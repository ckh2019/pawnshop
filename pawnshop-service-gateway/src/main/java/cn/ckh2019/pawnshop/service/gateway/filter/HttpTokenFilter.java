/*
package cn.ckh2019.pawnshop.service.gateway.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

*/
/**
 * @author Chen Kaihong
 * 2020-02-20 0:07
 *//*

@Component
@WebFilter(urlPatterns = "/goods/*", filterName = "authFilter")
public class HttpTokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        Cookie[] cookies = req.getCookies();
        Cookie cookie = new Cookie("token", "1234");
        cookie.setMaxAge(3600);
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.addCookie(cookie);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
*/
