package cn.ckh2019.pawnshop.commons.model.dto;

import lombok.Data;

/**
 * @author Chen Kaihong
 * 2020-02-21 21:11
 */
@Data
public class RegisterDto {

    private String phone;
    private String email;
    private String checkCode;
    private String password;
}
