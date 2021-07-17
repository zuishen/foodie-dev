package work.jimmmy.foodie.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ApiIgnore // 当前类的所有方法，不在文档中显示
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello() {
        return "Hello World!";
    }

    @GetMapping("/getSession")
    public Object setSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.setAttribute("userInfo", "new user");
        session.setMaxInactiveInterval(1000);
        System.out.println(session.getAttribute("userInfo"));
        return "ok";
    }
}
