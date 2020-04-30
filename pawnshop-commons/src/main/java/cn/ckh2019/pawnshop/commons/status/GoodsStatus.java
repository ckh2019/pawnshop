package cn.ckh2019.pawnshop.commons.bean;

/**
 * @author Chen Kaihong
 * 2020-02-25 23:18
 */
public class GoodsStatus {

    /**
     * 信息待完善 0
     * 待送审  1
     * 审核中  2
     * 已上架  3
     * 已下架  4
     */
    public static final int TO_BE_FINISH = 0;

    public static final int TO_BE_REVIEW = 1;

    public static final int REVIEWING = 2;

    public static final int PUBLIC = 3;

    public static final int WITHDREW = 4;
}
