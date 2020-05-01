package cn.ckh2019.pawnshop.service.goods.service;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import cn.ckh2019.pawnshop.commons.model.pojo.Goods;
import cn.ckh2019.pawnshop.commons.model.pojo.Price;
import com.github.pagehelper.PageInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author Chen Kaihong
 * 2020-02-24 12:56
 */
public interface GoodsService {

    List<Goods> addGoods(File f);

    String getPriceList(String gid) throws IOException;

    Result setPrice(File f, String gid) throws IOException;

    int setGoodsPhoto(String gid, String name);

    Business getGoodsByPhone(String principal);

    PageInfo<Goods> getGoods(Goods goods, Integer page, Integer rows);

    Result setStatus(String gid, Integer status);

    void refusePublic(String gid);

    PageInfo<Goods> getGoodsAdmin(Goods goods, Integer page, Integer rows);

    Result getMaxOrderNumGoods();

    Result getGoodsByName(String name);

    Result getGoodsDetailByGid(String gid);
}
