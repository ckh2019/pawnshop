package cn.ckh2019.pawnshop.service.cms;

import cn.ckh2019.pawnshop.commons.model.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Kaihong
 * 2019-07-30 21:24
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.ckh2019.pawnshop")
@EnableTransactionManagement
@RestController
@Api("用户信息管理")
public class PawnshopServiceCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PawnshopServiceCmsApplication.class, args);
    }

    @GetMapping("/userTest")
    @ApiOperation(value = "返回用户信息", notes = "测试")
    public User getTest () {
        User user = new User();
        user.setUid(10);
        user.setPassword("1234");
        return user;
    }
}
