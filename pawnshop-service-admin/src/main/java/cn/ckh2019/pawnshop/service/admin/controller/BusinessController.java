package cn.ckh2019.pawnshop.service.admin.controller;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.dto.BusinessDto;
import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import cn.ckh2019.pawnshop.commons.model.pojo.Goods;
import cn.ckh2019.pawnshop.commons.status.BusinessStatus;
import cn.ckh2019.pawnshop.service.admin.service.BusinessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Chen Kaihong
 * 2020-02-22 15:33
 */
@RestController
@RequestMapping("/admin")
public class BusinessController {

    @Autowired
    BusinessService businessService;

    /**
     * 商户入驻提交审核
     * @param business
     * @return
     */
    @PostMapping("/businessCheck")
    public Result businessCheck(Business business) {
        return businessService.businessCheck(business);
    }

    /**
     * 审核商户入驻申请
     * @param bid
     * @return
     */
    @GetMapping("/businessCheck/{bid}/{tag}")
    public Result businessCheck2(@PathVariable Integer bid, @PathVariable Integer tag) {
        if (tag == 0) {
            return businessService.agreeEnter(bid);
        } else if (tag == 1) {
            return businessService.refuseEnter(bid);
        }
        return null;
    }

    @GetMapping("/opBusiness/{bid}/{tag}")
    public Result opBusiness(@PathVariable Integer bid, @PathVariable Integer tag) {
        return businessService.opBusiness(bid,tag);
    }

    @RequestMapping("/getBusiness")
    public PageInfo<Business> getBusiness(Business business, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows) {
        System.out.println(business);
        return businessService.getBusiness(business,page,rows);
    }

    @RequestMapping("/getGoods")
    public PageInfo<Goods> getGoods(Goods goods, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows) {
        return businessService.getGoods(goods,page,rows);
    }

    /**
     * 设置店铺名
     * @param name
     * @return
     */
    @RequestMapping("/setName")
    public Result setName(String name) {
        return businessService.setName(name);
    }

    @RequestMapping("/getBusinessDto")
    public PageInfo<BusinessDto> getBusinessDto(String name, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows) {
        return businessService.getBusinessDto(name, page, rows);
    }
}

