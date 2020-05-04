package cn.ckh2019.pawnshop.service.user.controller;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.dto.RegisterDto;
import cn.ckh2019.pawnshop.service.user.service.UserService;
import com.mysql.cj.protocol.Resultset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * @author Chen Kaihong
 * 2019-07-31 14:33
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/sendRegisterCheckMeg/{phone}")
    public Result sendRegisterCheckMeg (@PathVariable String phone) {
        return userService.sendRegisterCheckMeg(phone);
    }

    @PostMapping("/register")
    public Result register(RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('p1')")
    public Result test(RegisterDto registerDto) {
        return new Result();
    }

    @GetMapping("/test2")
    @PreAuthorize("isAnonymous()")
    public Result test2(RegisterDto registerDto) {
        return new Result();
    }

    @GetMapping("/sendResetMeg/{principal}/{method}")
    public Result sendResetMeg(@PathVariable String principal, @PathVariable Integer method) {
        return userService.sendResetMeg(principal, method);
    }

    @PostMapping("/resetPwd")
    public Result resetPwd (String principal, String checkCode, String password) {
        return userService.resetPwd(principal, checkCode, password);
    }







}
