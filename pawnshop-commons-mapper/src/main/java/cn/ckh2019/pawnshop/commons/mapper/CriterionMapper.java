package cn.ckh2019.pawnshop.commons.mapper;

import cn.ckh2019.pawnshop.commons.model.pojo.Criterion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Chen Kaihong
 * 2020-02-24 18:13
 */
@Mapper
public interface CriterionMapper extends BaseMapper<Criterion> {

    @Select("select c.descInfo from criterion c INNER JOIN criterionitem i on c.cid = i.cid where i.id = #{id}")
    String queryInfoByItemId(Integer id);
}
