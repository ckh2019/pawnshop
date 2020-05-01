package cn.ckh2019.pawnshop.service.admin.service.impl;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.bean.Roles;
import cn.ckh2019.pawnshop.commons.mapper.BusinessMapper;
import cn.ckh2019.pawnshop.commons.mapper.GoodsMapper;
import cn.ckh2019.pawnshop.commons.mapper.OrderMapper;
import cn.ckh2019.pawnshop.commons.mapper.UserMapper;
import cn.ckh2019.pawnshop.commons.model.dto.BusinessDto;
import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import cn.ckh2019.pawnshop.commons.model.pojo.Goods;
import cn.ckh2019.pawnshop.commons.model.pojo.Order;
import cn.ckh2019.pawnshop.commons.model.pojo.User;
import cn.ckh2019.pawnshop.commons.status.BusinessStatus;
import cn.ckh2019.pawnshop.service.admin.config.RabbitmqConfig;
import cn.ckh2019.pawnshop.service.admin.service.BusinessService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-22 15:41
 */
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public Result businessCheck(Business business) {
        Result result = new Result();
        if ( businessMapper.insert(business) == 1) {
            result.setTag(true);
        }
        return result;
    }

    @Override
    public PageInfo<Business> getBusiness(Business business,Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Business> list = businessMapper.queryBusinessByAnd(business);
        return new PageInfo<Business>(list);
    }

    @Override
    public Result agreeEnter(Integer bid) {
        //生成账户
        Business business = businessMapper.selectById(bid);
        if (business != null) {
            User user = new User();
            user.setPhone(business.getPhone());
            user.setEmail(business.getEmail());
            String pwd = passwordEncoder.encode(user.getPhone().substring(7));
            user.setPassword(pwd);
            user.setRole(Roles.ROLE_BUSINESS);
            userMapper.insert(user);
            business.setStatus(1);
            businessMapper.updateById(business);
            String msg = user.getEmail() + "&当铺网&" + business.getCorporation() + ", 您好! 您的商户入驻申请已经审核通过,初始密码为您的手机号后4位";
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,RabbitmqConfig.INFORM_EMAIL, msg);



        }
        return null;
    }

    @Override
    public Result refuseEnter(Integer bid) {
        Result result = new Result();
        Business business = businessMapper.selectById(bid);
        String msg = business.getEmail() + "&当铺网&" + business.getCorporation() + ", 您好! 您的商户入驻申请信息不符合标准,已被驳回";
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,RabbitmqConfig.INFORM_EMAIL, msg);
        businessMapper.deleteById(bid);
        result.setTag(true);
        return null;
    }

    @Override
    public Result setLogo(String logoUrl) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QueryWrapper<Business> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", authentication.getPrincipal());
        Business business = businessMapper.selectOne(wrapper);
        business.setLogo(logoUrl);
        if (businessMapper.updateById(business) == 1) {
            return new Result(true);
        }
        return new Result();

    }

    @Override
    public Result setName(String name) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QueryWrapper<Business> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", authentication.getPrincipal());
        Business business = businessMapper.selectOne(wrapper);
        business.setName(name);
        if (businessMapper.updateById(business) == 1) {
            return new Result(true);
        }
        return new Result();

    }

    @Override
    public PageInfo<Goods> getGoods(Goods goods, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Goods> list =  goodsMapper.queryGoodsByAnd(goods);
        return new PageInfo<>(list);
    }

    @Override
    public Result opBusiness(Integer bid, Integer tag) {
        Business business = businessMapper.selectById(bid);
        business.setStatus(tag);
        String msg = business.getEmail() + "&当铺网&" + business.getCorporation();
        if (tag == BusinessStatus.FORBID) {
            msg += ", 您好! 您的商户违法规定,已被冻结";

        } else if (tag == BusinessStatus.NORMAL) {
            msg += ", 您好! 您的商户已经解封";
        }
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,RabbitmqConfig.INFORM_EMAIL, msg);
        return new Result(true);
    }

    @Override
    public PageInfo<BusinessDto> getBusinessDto(String name, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);

        List<Business> list = businessMapper.getBusinessDto(name);
        List<BusinessDto> dtos = new ArrayList<>();
        list.forEach(business -> {
            BusinessDto dto = new BusinessDto();
            dto.setBid(business.getBid());
            dto.setName(business.getName());
            dto.setCorporation(business.getCorporation());
            dto.setStatus(business.getStatus());
            QueryWrapper<Goods> wrapper = new QueryWrapper<>();
            wrapper.eq("bid", business.getBid());
            dto.setGoodsNum(goodsMapper.selectCount(wrapper));
            QueryWrapper<Order> wrapper1 = new QueryWrapper<>();
            wrapper.eq("bid", business.getBid());
            dto.setOrderSum(orderMapper.selectCount(wrapper1));
        });
        return new PageInfo<>(dtos);
    }
}
