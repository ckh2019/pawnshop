//package cn.ckh2019.pawnshop.service.email.service.impl;
//
//import cn.ckh2019.pawnshop.commons.model.pojo.User;
//import cn.ckh2019.pawnshop.service.email.mapper.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
///**
// * @author Chen Kaihong
// * 2020-02-12 23:17
// */
//@Service("userDetailsService")
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UserMapper userMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userMapper.getUserByEmailOrPhone(username);
//        if (user == null) {
//            return null;
//        }
//        UserDetails userDetails = org.springframework.security.core.userdetails.User.
//                withUsername(user.getEmail()).password(user.getPassword()).authorities("p1").build();
//        return userDetails;
//
//
//
//
//
//    }
//}
