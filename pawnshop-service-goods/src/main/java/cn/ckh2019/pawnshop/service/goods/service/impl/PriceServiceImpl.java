package cn.ckh2019.pawnshop.service.goods.service.impl;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.mapper.BusinessMapper;
import cn.ckh2019.pawnshop.commons.mapper.OrderMapper;
import cn.ckh2019.pawnshop.commons.mapper.PriceMapper;
import cn.ckh2019.pawnshop.commons.mapper.UserMapper;
import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import cn.ckh2019.pawnshop.commons.model.pojo.Order;
import cn.ckh2019.pawnshop.commons.model.pojo.Price;
import cn.ckh2019.pawnshop.commons.model.pojo.User;
import cn.ckh2019.pawnshop.commons.status.OrderStatus;
import cn.ckh2019.pawnshop.service.goods.service.PriceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-25 15:43
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceMapper priceMapper;

    @Override
    public Result getPrice(String str) {
        String[] strings = str.split("&");
        int[] ints = new int[strings.length-1];
        if (strings.length > 1) {
            for (int i = 1; i < strings.length; i ++) {
                ints[i-1] = Integer.parseInt(strings[i]);
            }
        }
        Arrays.sort(ints);
        String itemSet = ints[0] + "";
        for (int i = 1; i < ints.length; i ++) {
            itemSet += "&" + ints[i];
        }
        QueryWrapper<Price> wrapper = new QueryWrapper<>();
        wrapper.eq("itemSet", itemSet);

        Price price = priceMapper.selectOne(wrapper);
        Result result = new Result();
        result.setTag(true);
        result.setObj(price);

        return result;
    }
}
