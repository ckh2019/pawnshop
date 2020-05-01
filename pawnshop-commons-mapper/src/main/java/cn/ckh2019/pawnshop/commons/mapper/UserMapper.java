package cn.ckh2019.pawnshop.commons.mapper;

import cn.ckh2019.pawnshop.commons.model.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Chen Kaihong
 * 2019-07-31 10:32
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where email = #{principal} or phone = #{principal}")
    User getUserByEmailOrPhone(String principal);

    @Select("select * from user where email = #{email}")
    User getUserByEmail(String email);

    @Select("select * from user where phone = #{phone}")
    User getUserByPhone(String phone);

    @Update("update user set password = #{password} where email = #{principal} or phone = #{principal}")
    int updateByEmailOrPhone(String password, String principal);

    @Select("select * from user where role = #{role} and (email = #{principal} or phone = #{principal})")
    User login(String principal, String role);
}
