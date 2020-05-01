package cn.ckh2019.pawnshop.service.oauth2.service.impl;

import cn.ckh2019.pawnshop.commons.mapper.UserMapper;
import cn.ckh2019.pawnshop.commons.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Chen Kaihong
 * 2020-02-12 23:17
 */
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;


    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] strs = username.split("&");
        User u = userMapper.login(strs[0], strs[1]);
        if (u == null) {
            return null;
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.
                withUsername(u.getPhone()).
                password(u.getPassword()).
                roles(strs[1]).
                authorities(strs[1]).build();
        return userDetails;
    }
}
