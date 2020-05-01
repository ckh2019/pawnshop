package cn.ckh2019.pawnshop.service.goods.service;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.pojo.Order;
import com.github.pagehelper.PageInfo;

/**
 * @author Chen Kaihong
 * 2020-02-27 12:32
 */
public interface OrderService {
    PageInfo<Order> getOrderByBid(Order order, int page, int rows);

    Result startOrder(Integer pid);

    PageInfo<Order> getOrders(Order order, int page, int rows);

    Result setOrderStatus(Integer oid, Integer status);
}
