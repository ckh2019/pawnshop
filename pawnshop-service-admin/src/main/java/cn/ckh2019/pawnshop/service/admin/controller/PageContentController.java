package cn.ckh2019.pawnshop.service.admin.controller;

import cn.ckh2019.pawnshop.commons.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Chen Kaihong
 * 2020-02-20 11:48
 */
@RestController
@RequestMapping("/pageContent")
public class PageContentController {

    @GetMapping("/getCarouselImg")
    public Result getCarouselImg (HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Result result = new Result();
        result.setTag(true);
        result.setObj(new String[]{
                "http://www.ckh.com/assets/img/banner/b1.png",
                "http://www.ckh.com/assets/img/banner/b2.png",
                "http://www.ckh.com/assets/img/banner/b3.png"});
        return result;

    }
}