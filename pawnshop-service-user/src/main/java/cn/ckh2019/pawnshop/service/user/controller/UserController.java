package cn.ckh2019.pawnshop.service.user.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Chen Kaihong
 * 2019-07-31 14:33
 */

@Controller
@RequestMapping("user")
public class UserController {


    @RequestMapping("/")
    public String access() {
        return "user/main";
    }

    @RequestMapping("main")
    public String main(Model model) {
        List<User> list = userService.selectAll();
        model.addAttribute("list",list);
        return "user/main";
    }



    @RequestMapping("toLogin")
    public String toLogin(Model model) {
        return "user/login";
    }


}
