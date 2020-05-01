package cn.ckh2019.pawnshop.commons.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-23 18:38
 */
@Data
@TableName("goods")
public class Goods {

    @TableId
    private String gid;

    private String name;

    @TableField("putawayDate")
    private Date putawayDate;

    @TableField("goodsPhoto")
    private String goodsPhoto;

    /**
     * 商户id
     */
    private Integer bid;

    /**
     * 店面
     */
    private String bname;

    /**
     * 信息待完善 0
     * 待送审  1
     * 审核中  2
     * 已上架  3
     * 已下架  4
     */
    private Integer status;

    @TableField(exist = false)
    private List<Criterion> criterionList;

    @TableField(exist = false)
    private Integer orderSum;


}
