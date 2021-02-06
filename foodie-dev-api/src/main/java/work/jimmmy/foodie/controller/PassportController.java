package work.jimmmy.foodie.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

}
