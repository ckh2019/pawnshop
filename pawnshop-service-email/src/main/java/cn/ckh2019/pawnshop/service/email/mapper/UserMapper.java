package cn.ckh2019.pawnshop.service.email.mapper;


        import cn.ckh2019.pawnshop.commons.model.pojo.User;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Select;
        import tk.mybatis.mapper.MyMapper;

/**
 * @author Chen Kaihong
 * 2020-02-13 22:02
 */
@Mapper
public interface UserMapper extends MyMapper<User> {


    @Select("select * from user where email = #{username} or phone = #{username}")
    User getUserByEmailOrPhone(String username);



}
