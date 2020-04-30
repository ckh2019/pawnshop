package cn.ckh2019.pawnshop.commons.bean;

/**
 * @author Chen Kaihong
 * 2020-02-25 23:53
 */
public class OrderStatus {
    /**
     *卖方发起订单
     */
    public static final int SELLER_START_ORDER = 0;

    /**
     * 买方已接单
     */
    public static final int BUYER_ACCEPT_ORDER = 1;

    /**
     * 买方拒绝接单
     */
    public static final int BUYER_REFUSE_ORDER = 2;

    /**
     * 卖方已发货
     */
    public static final int SELLER_SEND = 3;

    /**
     * 买方超期未收货
     */
    public static final int BUYER_UN_RECEIVE = 4;

    /**
     * 买方收货
     */
    public static final int BUYER_RECEIVE = 5;

    /**
     * 买方已验收
     */
    public static final int BUYER_INSPECTION = 6;

    /**
     * 买方退货
     */
    public static final int BUYER_RETURNS = 7;

    /**
     * 卖方已收到退货
     */
    public static final int SELLER_RECEIVE = 8;

    /**
     *卖方超期未收到收到退货
     */
    public static final int SELLER_UN_RECEIVE = 9;

}
