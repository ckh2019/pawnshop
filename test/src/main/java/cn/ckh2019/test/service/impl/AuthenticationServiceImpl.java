package cn.ckh2019.test.service.impl;

import cn.ckh2019.test.model.AuthenticationRequest;
import cn.ckh2019.test.model.UserDto;
import cn.ckh2019.test.service.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Chen Kaihong
 * 2020-02-12 14:42
 */
@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    /**
     * 用户认证
     * @param authenticationRequest
     * @return
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null ||
                StringUtils.isEmpty(authenticationRequest.getUsername()) ||
                StringUtils.isEmpty(authenticationRequest.getPassword())) {
            throw new RuntimeException("账号或密码为空");
        }
        UserDto userDto = getUserDto(authenticationRequest.getUsername());
        userDto.setPassword("123");
        if (userDto == null) {
            throw new RuntimeException("用户不存在");
        } else if (!authenticationRequest.getPassword().equals(userDto.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        return userDto;
    }


    private UserDto getUserDto (String username) {
        return new UserDto();
    }
}
