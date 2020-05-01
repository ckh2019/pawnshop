package cn.ckh2019.pawnshop.commons.model.dto;

import cn.ckh2019.pawnshop.commons.model.pojo.Goods;
import lombok.Data;

/**
 * @author Chen Kaihong
 * 2020-03-06 12:59
 */
@Data
public class GoodsDto {

    private String gid;

    private Goods goods;

    private int num;

    private double maxPrice;
}
