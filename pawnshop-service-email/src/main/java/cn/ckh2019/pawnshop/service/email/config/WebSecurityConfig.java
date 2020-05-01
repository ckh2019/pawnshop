//package cn.ckh2019.pawnshop.service.email.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
///**
// * @author Chen Kaihong
// * 2020-02-12 18:08
// */
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     *
//     * @return
//     */
//    /*@Bean
//    public UserDetailsService userDetailsService () {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("123").authorities("p2").build());
//        return manager;
//    }*/
//
//    @Bean
//    public PasswordEncoder passwordEncoder () {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/r/a").hasAuthority("p1")
//                .antMatchers("/r/b").hasAuthority("p2")
//                .antMatchers("/r/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login-view")
//                .loginProcessingUrl("/login")
//                .successForwardUrl("/login-successs");
//    }
//}
