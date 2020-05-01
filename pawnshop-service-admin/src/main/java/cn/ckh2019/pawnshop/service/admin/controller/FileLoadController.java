package cn.ckh2019.pawnshop.service.admin.controller;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.service.admin.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @author Chen Kaihong
 * 2020-02-22 15:33
 */
@RestController
@RequestMapping("/admin")
public class FileLoadController {

    @Autowired
    BusinessService businessService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile file){
        Result result = new Result();
        String name = System.currentTimeMillis() + "" + new Random().nextInt(100000) + "." + file.getOriginalFilename().split("\\.")[1];
        try {
            file.transferTo(new File("D:\\app\\nginx-1.17.4\\pawnshop\\assets\\img/"+name));
            result.setMsg(name);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        result.setTag(true);

        return result;
    }

    /**
     * 上传店铺logo
     * @param file
     * @return
     */
    @RequestMapping("/uploadLogo")
    public Result uploadLogo(MultipartFile file){

        String logoUrl = System.currentTimeMillis() + "" + new Random().nextInt(100000) + "." + file.getOriginalFilename().split("\\.")[1];
        Result result = businessService.setLogo(logoUrl);
        try {
            file.transferTo(new File("D:\\app\\nginx-1.17.4\\pawnshop\\assets\\img/"+logoUrl));
            result.setMsg(logoUrl);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        result.setTag(true);

        return result;
    }
}
