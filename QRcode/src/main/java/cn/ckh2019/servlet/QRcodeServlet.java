package cn.ckh2019.servlet;

import cn.ckh2019.pojo.Code;
import cn.ckh2019.utils.DbUtils;
import cn.ckh2019.utils.QRCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Chen Kaihong
 * 2019-09-25 22:03
 */
@WebServlet("/code")
public class QRcodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String content = req.getParameter("content");
        ServletOutputStream os = resp.getOutputStream();
        String temp = content == null || content.trim().equals("") ? " " : content;
        new Thread(){
            @Override
            public void run() {
                Code code = new Code();
                code.setContent(temp);
                code.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                try {
                    DbUtils.insert(code);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        if(content == null || content.trim().equals("")){
            ImageIO.write(ImageIO.read(new File(req.getServletContext().getRealPath("/img/code.jpg"))),"jpg", os);
        } else {
            try {
                QRCodeUtils.encode(content,os );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
