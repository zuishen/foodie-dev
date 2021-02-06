package work.jimmmy.foodie.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.jimmmy.foodie.pojo.bo.UserBo;
import work.jimmmy.foodie.service.UserService;
import work.jimmy.foodie.common.utils.JsonResultResponse;

@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    private UserService userService;

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

    @PostMapping("/regist")
    public JsonResultResponse usernameIsExist(@RequestBody UserBo userBo) {
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

        // 判断两次秘密啊是否一致
        if (!password.equals(confirmPwd)) {
            return JsonResultResponse.errorMsg("两次输入的密码不一致");
        }

        userService.createUser(userBo);

        // 请求成功，用户名没有重复
        return JsonResultResponse.ok();
    }

}
