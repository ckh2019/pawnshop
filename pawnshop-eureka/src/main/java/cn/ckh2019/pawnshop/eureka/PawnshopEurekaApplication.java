package cn.ckh2019.pawnshop.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Chen Kaihong
 * 2019-11-30 22:26
 */
@EnableEurekaServer //标注此工程是一个Eureka Server
@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class})*/
public class PawnshopEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PawnshopEurekaApplication.class);
    }
}
