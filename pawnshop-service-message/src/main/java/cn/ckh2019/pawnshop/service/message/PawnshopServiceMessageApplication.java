package cn.ckh2019.pawnshop.service.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Chen Kaihong
 * 2020-02-10 22:03
 */
@SpringBootApplication
@MapperScan("cn.ckh2019.pawnshop.service.email.mapper")
public class PawnshopServiceEmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(PawnshopServiceEmailApplication.class,args);
    }
}
