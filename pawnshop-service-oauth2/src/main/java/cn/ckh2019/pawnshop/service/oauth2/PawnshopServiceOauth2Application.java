package cn.ckh2019.pawnshop.service.oauth2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author Chen Kaihong
 * 2020-02-18 12:01
 */
@SpringBootApplication
@MapperScan(basePackages = "cn.ckh2019.pawnshop.commons.mapper")
@EnableDiscoveryClient
public class PawnshopServiceOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(PawnshopServiceOauth2Application.class, args);
    }
}
