package cn.ckh2019.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author Chen Kaihong
 * 2020-02-11 20:31
 */
@Configuration
@ComponentScan(basePackages = "cn.ckh2019.test", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class ApplicationConfig {

}
