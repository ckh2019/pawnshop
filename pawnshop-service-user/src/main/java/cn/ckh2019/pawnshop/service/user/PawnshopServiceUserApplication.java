package cn.ckh2019.pawnshop.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Chen Kaihong
 * 2019-07-31 14:23
 */
@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@MapperScan(basePackages = "cn.ckh2019.pawnshop.commons.mapper")
@SpringBootApplication(scanBasePackages = "cn.ckh2019.pawnshop")
public class PawnshopServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PawnshopServiceUserApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
