package cn.ckh2019.pawnshop.commons.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-23 19:11
 */
@Data
@TableName("price")
public class Price {

    @TableId(type = IdType.AUTO)
    private Integer pid;

    @TableField("itemSet")
    private String itemSet;

    private Double price;

    /**
     * 商品id
     */
    private String gid;

    /**
     * 商户id
     */
    private Integer bid;

    @TableField(exist = false)
    private List<String> infoSet;

}
