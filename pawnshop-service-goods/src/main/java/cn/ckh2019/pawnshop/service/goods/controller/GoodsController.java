package cn.ckh2019.pawnshop.service.goods.controller;

import cn.ckh2019.pawnshop.commons.status.GoodsStatus;
import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import cn.ckh2019.pawnshop.commons.model.pojo.Goods;
import cn.ckh2019.pawnshop.commons.model.pojo.User;
import cn.ckh2019.pawnshop.service.goods.service.GoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Chen Kaihong
 * 2020-02-14 23:35
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GoodsService goodsService;

    @GetMapping(value = "/buy/{uid}")
    @PreAuthorize(("hasAnyRole('admin')"))
    public Result getUser (@PathVariable Integer uid) {

        //User user = goodsFeignClient.sendEmail("3170865835@qq.com", "hello");
        Result result = restTemplate.getForObject("http://pawnshop-service-email/email/sendEmail/3170865835@qq.com/hello", Result.class);
        return result;
    }


    /**
     * 添加商品
     * @param file
     * @return
     */
    @RequestMapping("/addGoods")
    public Result addGoods(MultipartFile file){
        Result result = new Result();
        String name = System.currentTimeMillis() + "" + new Random().nextInt(100000) + "." + file.getOriginalFilename().split("\\.")[1];
        try {
            File  f= new File("D:\\app\\nginx-1.17.4\\pawnshop\\assets\\excel/"+name);
            if(!f.exists()){
               // System.out.println("文件已经存在！");
                f.createNewFile();
            }
            file.transferTo(f);
            List<Goods> goodsList = goodsService.addGoods(f);
            result.setMsg(name);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        result.setTag(true);

        return result;
    }


    /**
     * 获取设置价格表格路径
     * @param gid
     * @return
     */
    @RequestMapping("/getPriceExcel/{gid}")
    public Result getPriceExcel(@PathVariable String gid) {
        Result result = new Result();
        try {
            result.setMsg(goodsService.getPriceList(gid));
            result.setTag(true);
        } catch (Exception e) {
            result.setTag(false);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过上传excel进行价格设置
     * @param file 上传文件名称
     * @param gid 商品id
     * @return
     */
    @RequestMapping("/setPrice/{gid}")
    public Result setPrice (MultipartFile file,@PathVariable String gid) {
        String name = System.currentTimeMillis() + "" + new Random().nextInt(100000) + "." + file.getOriginalFilename().split("\\.")[1];
        try {
            File  f= new File("D:\\app\\nginx-1.17.4\\pawnshop\\assets\\excel/"+name);
            if(!f.exists()){
                f.createNewFile();
            }
            file.transferTo(f);
            return goodsService.setPrice(f, gid);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return  null;
    }


    /**
     * 上传存储商品的图片
     * @param file
     * @param gid
     * @return
     */
    @RequestMapping("/setGoodsPhoto/{gid}")
    public Result setGoodsPhoto(MultipartFile file, @PathVariable String gid){
        Result result = new Result();
        String name = System.currentTimeMillis() + "" + new Random().nextInt(100000) + "." + file.getOriginalFilename().split("\\.")[1];
        try {
            File  f= new File("D:\\app\\nginx-1.17.4\\pawnshop\\assets\\img/"+name);
            if(!f.exists()){
                // System.out.println("文件已经存在！");
                f.createNewFile();
            }
            file.transferTo(f);
            int goodsList = goodsService.setGoodsPhoto(gid, name);
            result.setMsg(name);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        result.setTag(true);

        return result;
    }

    /**
     * 获取商品列表
     * @param goods
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/getGoods")
    public PageInfo<Goods> getGoodsByGid(Goods goods, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Business b = goodsService.getGoodsByPhone(authentication.getPrincipal().toString());
        if (goods == null) {
            goods = new Goods();
        }
        //goods.setBid(b.getBid());
        goods.setBid(1);
        return goodsService.getGoods(goods, page, rows);
    }

    /**
     * 商品提交送审
     * @param gid
     * @return
     */
    @RequestMapping("/goodsCheck/{gid}")
    public Result goodsCheck(@PathVariable String gid) {

        return goodsService.setStatus(gid, GoodsStatus.REVIEWING);
    }

    /**
     * 商品下架
     * @param gid
     * @return
     */
    @RequestMapping("/withdraw/{gid}")
    public Result withdraw (@PathVariable String gid) {
        return goodsService.setStatus(gid, GoodsStatus.WITHDREW);
    }

    @RequestMapping("/public/{gid}")
    public Result publicGoods (@PathVariable String gid) {
        return goodsService.setStatus(gid, GoodsStatus.PUBLIC);
    }

    /**
     * 商品审核
     * @param gid
     * @param status
     * @return
     */
    @RequestMapping("/reviewGoods/{gid}/{status}")
    public Result reviewGoods (@PathVariable String gid, @PathVariable Integer status) {
        if (status == GoodsStatus.TO_BE_REVIEW) {
            goodsService.refusePublic(gid);
        }
        return goodsService.setStatus(gid, status);
    }


    @GetMapping(value = "/test")
    //@PreAuthorize("hasAnyAuthority('p11')")
    public Result test (@RequestHeader("token") String token) {
        System.out.println(new ArrayList<>(SecurityContextHolder.getContext().
                getAuthentication().getAuthorities()).get(0)
                );


        //User user = goodsFeignClient.sendEmail("3170865835@qq.com", "hello");
        //Result result = restTemplate.getForObject("http://pawnshop-service-email/email/sendEmail/3170865835@qq.com/hello", Result.class);
        return new Result(false, "1");
    }

    @GetMapping(value = "/test2")
    @PreAuthorize("hasAnyAuthority('p2')")
    public Result test2 () {
        return new Result(false, "2");
    }

    @GetMapping(value = "/test3")
    public Result test3 () throws IOException {
        Result result = new Result();
        //result.setObj();
        return result;
    }



    /*private UserDetails getUserDetails() {

        UserDetails userDetails = (UserDetails)
        return userDetails;

    }*/
    @RequestMapping("/getAllGoods")
    public PageInfo<Goods> getGoodsAdmin (Goods goods, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows) {

        return goodsService.getGoodsAdmin(goods, page, rows);

    }

    @RequestMapping("/getMaxOrderNumGoods")
    public Result getMaxOrderNumGoods () {
        return goodsService.getMaxOrderNumGoods();
    }

    @RequestMapping("/getGoodsByName")
    public Result getGoodsByName(String name){
        return goodsService.getGoodsByName(name);
    }

    @RequestMapping("/getGoodsDetailByGid/{gid}")
    public Result getGoodsDetailByGid(@PathVariable String gid) {
        return goodsService.getGoodsDetailByGid(gid);
    }


}
