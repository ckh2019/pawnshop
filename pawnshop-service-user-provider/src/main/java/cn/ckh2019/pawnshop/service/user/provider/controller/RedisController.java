package cn.ckh2019.pawnshop.service.user.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Chen Kaihong
 * 2019-08-19 16:06
 */
@RestController
public class RedisController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis")
    public String test()  {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");

        return "OK";
    }

    @GetMapping("/redis1")
    public String test2(HttpSession session)  {

        // 保存字符串
        //stringRedisTemplate.opsForValue().set("aaa", "111");
        session.setAttribute("ckh","ckh");
        return "OK1";
    }
}
