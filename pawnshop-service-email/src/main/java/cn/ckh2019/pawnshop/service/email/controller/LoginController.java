package cn.ckh2019.pawnshop.service.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Kaihong
 * 2020-02-12 16:14
 */
@RestController
public class LoginController {


    /*@RequestMapping(value = "/login", produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest) {
        UserDto userDto = authenticationService.authentication(authenticationRequest);
        return userDto.getUsername() + "登录成功";

    }*/


    @RequestMapping(value = "/login-successs", produces = "text/plain;charset=utf-8")
    public String loginSuccess() {
        return " login success ...";
    }
}
