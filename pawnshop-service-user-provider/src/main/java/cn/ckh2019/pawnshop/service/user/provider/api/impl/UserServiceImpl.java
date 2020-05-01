package cn.ckh2019.pawnshop.service.user.provider.api.impl;

import cn.ckh2019.pawnshop.commons.mapper.UserMapper;
import cn.ckh2019.pawnshop.service.user.api.service.UserService;
import cn.ckh2019.pawnshop.commons.bean.User;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Chen Kaihong
 * 2019-07-31 10:30
 */
@Service(version = "${services.versions.user.v1}")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> findByName(String username) {
        Example example = new Example(User.class);//实例化
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("username", username);
        List<User> userRoleList = userMapper.selectByExample(example);
        return userRoleList;
    }
}
