package cn.ckh2019.test.service;

import cn.ckh2019.test.model.AuthenticationRequest;
import cn.ckh2019.test.model.UserDto;

/**
 * @author Chen Kaihong
 * 2020-02-12 14:40
 */
public interface AuthenticationService {

    UserDto authentication(AuthenticationRequest authenticationRequest);
}
