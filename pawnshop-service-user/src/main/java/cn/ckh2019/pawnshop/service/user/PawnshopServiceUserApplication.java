package cn.ckh2019.pawnshop.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

/**
 * @author Chen Kaihong
 * 2019-07-31 14:23
 */
@EnableHystrix
@EnableHystrixDashboard
@EnableRedisHttpSession
@SpringBootApplication(scanBasePackages = "cn.ckh2019.pawnshop")
public class PawnshopServiceUserConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PawnshopServiceUserConsumerApplication.class,args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
