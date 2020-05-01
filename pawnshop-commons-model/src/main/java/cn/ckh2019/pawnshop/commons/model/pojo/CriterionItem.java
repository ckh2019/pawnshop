package cn.ckh2019.pawnshop.commons.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Chen Kaihong
 * 2020-02-23 18:56
 */
@Data
@TableName("criterionItem")
public class CriterionItem {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 指标id
     */
    private String cid;

    /**
     * 商户id
     */
    private Integer bid;


    /**
     * 商品id
     */
    private String gid;

    @TableField("descInfo")
    private String descInfo;
}
