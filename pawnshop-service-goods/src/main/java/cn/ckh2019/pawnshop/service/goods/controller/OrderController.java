package cn.ckh2019.pawnshop.service.goods.controller;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.pojo.Order;
import cn.ckh2019.pawnshop.service.goods.service.OrderService;
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
 * 2020-02-26 13:03
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    PriceService priceService;

    @Autowired
    OrderService orderService;

    /**
     * 用户发起交易
     * @return
     */
    @RequestMapping("/startOrder/{pid}")
    public Result startOrder(@PathVariable Integer pid) {
        return orderService.startOrder(pid);
    }

    @RequestMapping("/getOrderByBid")
    public PageInfo<Order> getOrderByBid(Order order, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int rows) {
        return orderService.getOrderByBid(order, page, rows);
    }

    @RequestMapping("/getOrders")
    public PageInfo<Order> getOrders(Order order, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int rows) {
        return orderService.getOrders(order, page, rows);
    }

    @RequestMapping("/setOrderStatus/{oid}/{status}")
    public Result setOrderStatus(@PathVariable Integer oid, @PathVariable Integer status) {
        return orderService.setOrderStatus(oid, status);
    }
}
