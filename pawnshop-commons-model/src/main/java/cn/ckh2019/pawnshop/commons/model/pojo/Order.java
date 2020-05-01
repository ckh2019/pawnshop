package cn.ckh2019.pawnshop.commons.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author Chen Kaihong
 * 2020-02-25 23:48
 */
@TableName("orders")
@Data
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer oid;
    /**
     * 价格id
     */
    private Integer pid;

    private Integer uid;

    /**
     * 当铺id
     */
    private Integer bid;

    /**
     * 商品id
     */
    private String gid;

    private Integer status;

    private Double price;

    /**
     *卖方发起订单时间
     */
    private Date seller_start_order_date;

    /**
     * 买方接单时间
     */
    private Date buyer_accept_order_date;

    /**
     * 买方拒绝接单时间
     */
    private Date buyer_refuse_order_date;

    /**
     * 卖方发货时间
     */
    private Date seller_send_date;

    /**
     * 买方收货
     */
    private Date buyer_receive_date;

    /**
     * 买方超期未收货
     */
    public Date buyer_un_receive_date;

    /**
     * 买方已验收
     */
    private Date buyer_inspection_date;

    /**
     * 买方发起退货退货
     */
    private Date buyer_returns_date;

    /**
     * 卖方已收到退货时间
     */
    private Date seller_receive_date;

    /**
     *卖方超期未收到收到退货
     */
    public Date seller_un_receive_date;
}
