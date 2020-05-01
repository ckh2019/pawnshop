package cn.ckh2019.pawnshop.service.user.service;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.dto.RegisterDto;

/**
 * @author Chen Kaihong
 * 2020-02-21 14:19
 */
public interface UserService {
    Result sendRegisterCheckMeg(String phone);

    Result register(RegisterDto registerDto);

    Result sendResetMeg(String principal, Integer method);

    Result resetPwd(String principal, Integer method, String checkCode, String password);
}
