package cn.ckh2019.pawnshop.commons.mapper;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.dto.GoodsDto;
import cn.ckh2019.pawnshop.commons.model.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-26 12:54
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> queryOrderByAnd(Order o);

    @Update("update orders set status = #{status} where oid = #{oid}")
    int updStatus(int oid, int status);

    @Select("select gid, COUNT(*) num from orders GROUP BY gid ORDER BY num desc LIMIT 0,4 ")
    List<GoodsDto> getMaxOrderNumGoods();


    @Select("select g.gid, COUNT(*) num from orders o RIGHT JOIN goods g on o.gid = g.gid " +
            "where g.name like '%${name}%' and g.status = 3 GROUP BY o.gid ORDER BY num desc ")
    List<GoodsDto> getGoodsByName(String name);

}
