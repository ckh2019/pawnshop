package cn.ckh2019.pawnshop.service.email.controller;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.pojo.User;
import cn.ckh2019.pawnshop.service.email.service.MailService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Kaihong
 * 2020-02-15 17:30
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    MailService mailService;

    @GetMapping(value = "/sendEmail/{to}/{msg}")
    @HystrixCommand(fallbackMethod = "processHystrix_sendEmail")
    public Result sendEmail (@PathVariable String to, @PathVariable String msg) {
        System.out.println(to);
        mailService.sendSimpleMail(to, "验证码", msg);
        int a = 0/0;
        return new Result(true, "email send success!");
    }

    public Result processHystrix_sendEmail ( String to, String msg) {
        return new Result(false);
    }
}
