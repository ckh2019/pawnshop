package cn.ckh2019.pawnshop.commons.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-23 18:55
 */
@Data
@TableName("criterion")
public class Criterion {

    @TableId
    private String cid;

    /**
     * 商品id
     */
    private String gid;

    /**
     * 商户id
     */
    private Integer bid;

    @TableField("descInfo")
    private String descInfo;

    @TableField(exist = false)
    private List<CriterionItem> itemList;
}
