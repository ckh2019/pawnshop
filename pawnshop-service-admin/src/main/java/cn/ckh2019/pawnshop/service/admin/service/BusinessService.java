package cn.ckh2019.pawnshop.service.admin.service;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.dto.BusinessDto;
import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import cn.ckh2019.pawnshop.commons.model.pojo.Goods;
import com.github.pagehelper.PageInfo;
//import com.github.pagehelper.PageInfo;

/**
 * @author Chen Kaihong
 * 2020-02-22 15:41
 */
public interface BusinessService {

    Result businessCheck(Business business);



    PageInfo<Business> getBusiness(Business business,Integer page, Integer rows);

    Result agreeEnter(Integer bid);

    Result refuseEnter(Integer bid);

    Result setLogo(String logoUrl);

    Result setName(String name);

    PageInfo<Goods> getGoods(Goods goods, Integer page, Integer rows);

    Result opBusiness(Integer bid, Integer tag);

    PageInfo<BusinessDto> getBusinessDto(String name, Integer page, Integer rows);
}
