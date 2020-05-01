package cn.ckh2019.pawnshop.commons.mapper;

import cn.ckh2019.pawnshop.commons.model.pojo.Price;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author Chen Kaihong
 * 2020-02-24 18:14
 */
@Mapper
public interface PriceMapper extends BaseMapper<Price> {

    @Update("update price set price = #{price} where pid = #{pid}")
    int setPrice(Integer pid, Double price);

    //int count
    @Select("select MAX(price) from price where gid = #{gid}")
    double getMaxPriceByGid(String gid);

    @Select("select price from price where itemSet = #{itemSet}")
    double getPriceByItemSet(String itemSet);
}
