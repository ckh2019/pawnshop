package cn.ckh2019.servlet;

import cn.ckh2019.pojo.Code;
import cn.ckh2019.utils.DbUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chen Kaihong
 * 2019-09-26 23:14
 */
@WebFilter("/img/ckh.png")
public class ImgFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        new Thread(){
            @Override
            public void run() {
                Code code = new Code();
                code.setContent("###img###");
                code.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                try {
                    DbUtils.insert(code);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
