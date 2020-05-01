package cn.ckh2019.servlet;

import cn.ckh2019.utils.TranslateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chen Kaihong
 * 2019-09-28 22:29
 */
@WebServlet("/translate")
public class TranslateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        String content = req.getParameter("content");
        String to = req.getParameter("to");
        String dst = new TranslateUtils().translate(content, to);
        resp.getWriter().println("{\"dst\":\"" + dst + "\"}");
    }
}
