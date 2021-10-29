package work.jimmmy.foodie.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.jimmmy.foodie.pojo.Users;
import work.jimmmy.foodie.pojo.bo.UserBo;
import work.jimmmy.foodie.service.UserService;
import work.jimmy.foodie.common.utils.CookieUtils;
import work.jimmy.foodie.common.utils.JsonResultResponse;
import work.jimmy.foodie.common.utils.JsonUtils;
import work.jimmy.foodie.common.utils.Md5Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JsonResultResponse usernameIsExist(@RequestParam String username) {
        // 判断用户名不为空
        if (StringUtils.isEmpty(username)) {
            return JsonResultResponse.errorMsg("用户名不能为空");
        }

        // 查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameExist(username);
        if (isExist) {
            return JsonResultResponse.errorMsg("用户名已经存在");
        }

        // 请求成功，用户名没有重复
        return JsonResultResponse.ok();
    }

    // value 接口名称 notes 接口说明
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/regist")
    public JsonResultResponse registry(@RequestBody UserBo userBo, HttpServletRequest req, HttpServletResponse resp) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPwd = userBo.getConfirmPassword();

        // 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return JsonResultResponse.errorMsg("用户名或密码不能为空");
        }

        // 查询用户名是否存在
        boolean isExist = userService.queryUsernameExist(username);
        if (isExist) {
            return JsonResultResponse.errorMsg("用户名已经存在");
        }

        // 密码长度不能少于6位
        if (password.length() < 6) {
            return JsonResultResponse.errorMsg("密码长度不能少于6");
        }

        // 判断两次密码是否一致
        if (!password.equals(confirmPwd)) {
            return JsonResultResponse.errorMsg("两次输入的密码不一致");
        }

        Users userResult = userService.createUser(userBo);
        setNullProperty(userResult);

        CookieUtils.setCookie(req, resp, "user", JsonUtils.objectToJson(userResult), true);
        // TODO: 生成用户token，存入redis会话
        // TODO: 同步购物车数据

        // 请求成功，用户名没有重复
        return JsonResultResponse.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JsonResultResponse login(@RequestBody @ApiParam(name = "userBo", value = "用户模型", required = true)
                                                UserBo userBo,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();

        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return JsonResultResponse.errorMsg("用户名或密码不能为空");
        }

        Users userResult = null;
        try {
            userResult = userService.queryUserForLogin(username, Md5Utils.getMD5Str(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userResult == null) {
            return JsonResultResponse.errorMsg("用户名密码不匹配");
        }

        setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        // TODO: 生成用户token，存入redis会话
        // TODO: 同步购物车数据

        return JsonResultResponse.ok(userResult);
    }

    @ApiOperation(value = "用户注销", notes = "用户注销", httpMethod = "POST")
    @PostMapping("/logout")
    public JsonResultResponse logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        // 清除用户相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");
        // TODO: 用户退出登录，需要清空购购物车
        // TODO: 分布式会话中需要清除用户数据
        return JsonResultResponse.ok();
    }

    private void setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
    }
}
