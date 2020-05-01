package cn.ckh2019.pawnshop.service.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Chen Kaihong
 * 2020-02-20 11:46
 */

@MapperScan(basePackages = "cn.ckh2019.pawnshop.commons.mapper")
@SpringBootApplication(scanBasePackages = "cn.ckh2019.pawnshop")
public class PawnshopServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PawnshopServiceAdminApplication.class, args);
    }
}
