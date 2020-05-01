package cn.ckh2019.pawnshop.service.goods.controller;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import cn.ckh2019.pawnshop.service.goods.service.PriceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Kaihong
 * 2020-02-25 15:36
 */
@RestController
@RequestMapping("/goods")
public class PriceController {

    @Autowired
    PriceService priceService;

    @RequestMapping("/getPrice/{str}")
    public Result getPrice (@PathVariable String str) {
       return priceService.getPrice(str);

    }




}
