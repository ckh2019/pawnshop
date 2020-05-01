package cn.ckh2019.pawnshop.service.email;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author Chen Kaihong
 * 2020-02-10 22:03
 */
@SpringBootApplication
@MapperScan("cn.ckh2019.pawnshop.service.email.mapper")
@EnableHystrix
@EnableHystrixDashboard
//@EnableDiscoveryClient
public class PawnshopServiceEmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(PawnshopServiceEmailApplication.class,args);
    }
}
