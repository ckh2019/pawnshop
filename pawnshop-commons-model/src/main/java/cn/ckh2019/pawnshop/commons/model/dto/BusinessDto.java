package cn.ckh2019.pawnshop.commons.model.dto;

import lombok.Data;

/**
 * @author Chen Kaihong
 * 2020-02-27 15:11
 */
@Data
public class BusinessDto {

    private int bid;

    private String corporation;

    private int goodsNum;

    private int orderNumToday;

    private String name;

    private int status;

    private int orderSum;
}
