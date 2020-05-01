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
import cn.ckh2019.pawnshop.service.goods.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-27 12:32
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PriceMapper priceMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    BusinessMapper businessMapper;

    @Override
    public PageInfo<Order> getOrderByBid(Order order, int page, int rows) {
        PageHelper.startPage(page, rows);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QueryWrapper<Business> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", authentication.getPrincipal());
        Business b = businessMapper.selectOne(wrapper);
        order.setBid(b.getBid());
        List<Order> list = orderMapper.queryOrderByAnd(order);
        return new PageInfo<>(list);
    }

    @Override
    public Result startOrder(Integer pid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", authentication.getPrincipal());
        wrapper.eq("role", "user");
        User u = userMapper.selectOne(wrapper);
        Price price = priceMapper.selectById(pid);
        Order order = new Order();
        order.setBid(price.getBid());
        order.setGid(price.getGid());
        order.setPid(price.getPid());
        order.setUid(u.getUid());
        order.setPrice(price.getPrice());
        order.setStatus(OrderStatus.SELLER_START_ORDER);
        order.setSeller_start_order_date(new Date());
        Result result = new Result();
        if (orderMapper.insert(order) == 1) {
            result.setTag(true);
        }
        return result;
    }

    @Override
    public PageInfo<Order> getOrders(Order order, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Order> list = orderMapper.queryOrderByAnd(order);
        return new PageInfo<>(list);
    }

    @Override
    public Result setOrderStatus(Integer oid, Integer status) {
        if (orderMapper.updStatus(oid, status) == 1) {
            return new Result(true);
        }
        return new Result();
    }
}
