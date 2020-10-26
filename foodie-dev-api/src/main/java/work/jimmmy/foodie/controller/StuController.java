package work.jimmmy.foodie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import work.jimmmy.foodie.pojo.Stu;
import work.jimmmy.foodie.service.StuService;

@RestController
public class StuController {
    @Autowired
    private StuService service;

    @GetMapping("/stu/{id}")
    public Stu getStu(@PathVariable String id) {
        return service.getStu(id);
    }
}
