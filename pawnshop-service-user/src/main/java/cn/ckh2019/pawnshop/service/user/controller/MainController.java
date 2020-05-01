package cn.ckh2019.pawnshop.service.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author Chen Kaihong
 * 2019-08-03 20:49
 */
@Controller
public class MainController {

    @GetMapping("a")
    @ResponseBody
    public String test(HttpSession session){
        return (String) session.getAttribute("ckh");
    }

    @RequestMapping(value = {"index.html","","index"})
    public String index() {
        return "index";
    }

    @GetMapping("main")
    public String main() {
        return "main";
    }
}
