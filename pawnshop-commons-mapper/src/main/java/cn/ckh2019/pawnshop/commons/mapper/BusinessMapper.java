package cn.ckh2019.pawnshop.commons.mapper;

import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-22 15:43
 */
@Mapper
public interface BusinessMapper extends BaseMapper<Business> {

    List<Business> queryBusinessByAnd(Business business);

    @Select("select * from business where name like '%${name}%' and status != 0")
    List<Business> getBusinessDto(String name);
}
