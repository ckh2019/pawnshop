package cn.ckh2019.pawnshop.commons.mapper;

import cn.ckh2019.pawnshop.commons.model.dto.GoodsDto;
import cn.ckh2019.pawnshop.commons.model.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-24 18:12
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    @Update("update goods set goodsPhoto = #{goodsPhoto} where gid = #{gid}")
    int setPhoto(String gid, String goodsPhoto);

    List<Goods> queryGoodsByAnd(Goods goods);

    @Update("update goods set status = #{status} where gid = #{gid}")
    int setStatus(String gid, Integer status);

    @Select("select gid, COUNT(*) num from orders GROUP BY gid ORDER BY num desc LIMIT 0,4 ")
    List<GoodsDto> getGoodsDtoByName();

    @Select("select * from goods where name like '%' + ${key} +'%'")
    List<Goods> searchGoods(String key);


}
