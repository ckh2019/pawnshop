/*
package cn.ckh2019.pawnshop.service.gateway.config;

import cn.ckh2019.pawnshop.service.gateway.filter.HttpTokenFilter;
import cn.ckh2019.pawnshop.service.gateway.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * @author Chen Kaihong
 * 2020-02-20 0:20
 *//*

@Configuration
public class GatewayConfig {

    @Bean
    public FilterRegistrationBean getServletRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new HttpTokenFilter());
        //bean.addInitParameter("param", "zhangsan");
        bean.addUrlPatterns("/goods/*");
        return bean;
    }

    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }

}
*/
