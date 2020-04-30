package cn.ckh.servlet;

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
        String content = req.getParameter("content");
        ServletOutputStream os = resp.getOutputStream();
        if(content == null || content.trim() == ""){
            ImageIO.write(ImageIO.read(new File(req.getServletContext().getRealPath("/img/new.jpg"))),"jpg", os);
        } else {
            try {
                QRCodeUtils.encode(content,os );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
