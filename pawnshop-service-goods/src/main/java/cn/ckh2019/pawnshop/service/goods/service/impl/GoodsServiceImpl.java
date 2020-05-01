package cn.ckh2019.pawnshop.service.goods.service.impl;

import cn.ckh2019.pawnshop.commons.model.dto.GoodsDto;
import cn.ckh2019.pawnshop.commons.status.GoodsStatus;
import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.mapper.*;
import cn.ckh2019.pawnshop.commons.model.pojo.*;
import cn.ckh2019.pawnshop.commons.status.GoodsStatus;
import cn.ckh2019.pawnshop.service.goods.config.RabbitmqConfig;
import cn.ckh2019.pawnshop.service.goods.service.GoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

/**
 * @author Chen Kaihong
 * 2020-02-24 13:01
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CriterionMapper criterionMapper;

    @Autowired
    CriterionItemMapper itemMapper;

    @Autowired
    PriceMapper priceMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    @Transactional
    public List<Goods> addGoods(File f) {
        //System.out.println(.getPrincipal());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        QueryWrapper<Business> qryWrapper = new QueryWrapper<>();
        qryWrapper.eq("phone", authentication.getPrincipal());
        Business b = businessMapper.selectOne(qryWrapper);
        //user.setRole(new ArrayList<>(authentication.getAuthorities()).get(0).toString());
        System.out.println(b);



        FileInputStream fis = null;
        Workbook workbook = null;
        List<Goods> goodsList = new ArrayList<>();
        try {

            fis = new FileInputStream(f);
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            int cols = sheet.getLastRowNum();

            List<Integer> goodsIndexList = new ArrayList<>();
            for (int i = 0; i <= cols; i ++) {
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String goodsName = row.getCell(0).toString();
                    if (goodsName != null && !goodsName.trim().equals("") ) {
                        Goods goods = new Goods();
                        goods.setBid(b.getBid());
                        goods.setGid(UUID.randomUUID().toString());
                        goods.setName(goodsName);
                        goods.setStatus(GoodsStatus.TO_BE_FINISH);
                        goods.setPutawayDate(new Date());
                        goodsList.add(goods);
                        goodsIndexList.add(i);
                        List<Criterion> criterionList = new ArrayList<>();

                        int j = 1;
                        while (true) {
                            Cell cells = row.getCell(j);
                            if (cells == null) {
                                break;
                            }
                            String criterionName = cells.toString();
                            if (criterionName == null || criterionName.trim().equals("")) {
                                break;
                            }
                            j ++;
                            Criterion criterion = new Criterion();
                            criterion.setGid(goods.getGid());
                            criterion.setCid(UUID.randomUUID().toString());
                            criterion.setBid(b.getBid());
                            criterion.setDescInfo(criterionName);
                            criterionList.add(criterion);
                        }
                        goods.setCriterionList(criterionList);
                    }
                }
            }
            int goodsNum = goodsIndexList.size();
            for (int i = 0; i < goodsNum; i ++) {
                int max = i == goodsNum-1 ? cols + 1 : goodsIndexList.get(i+1);
                Goods goods = goodsList.get(i);
                goodsMapper.insert(goods);
                List<Criterion> criterionList = goods.getCriterionList();
                int criterionListSize = criterionList.size();
                for (int j = 1; j <= criterionListSize; j ++) {
                    criterionMapper.insert(criterionList.get(j-1));
                    List<CriterionItem> itemList = new ArrayList<>();
                    Criterion c = criterionList.get(j-1);
                    for (int n = goodsIndexList.get(i)+1; n < max; n ++) {
                        Cell cell2 = sheet.getRow(n).getCell(j);
                        if (cell2 == null) {
                            break;
                        }
                        String itemStr = cell2.toString();
                        if (itemStr == null || itemStr.trim().equals("")) {
                            break;
                        }
                        CriterionItem item = new CriterionItem();
                        item.setGid(goods.getGid());
                        item.setCid(c.getCid());
                        item.setDescInfo(itemStr);
                        itemList.add(item);
                        item.setBid(b.getBid());
                        itemMapper.insert(item);
                    }
                    c.setItemList(itemList);
                }
            }

            goodsList.forEach(goods -> {
                List<Criterion> criterionList = goods.getCriterionList();
                List<List<String>> list = new ArrayList<>();
                criterionList.forEach(criterion -> {
                    QueryWrapper<CriterionItem> wrapper = new QueryWrapper<>();
                    wrapper.eq("cid", criterion.getCid());
                    //查询一个指标的所有指标项
                    List<CriterionItem> itemList = itemMapper.selectList(wrapper);
                    //将同一指标的指标项的id存入集合
                    List<String> strings = new ArrayList<>();
                    itemList.forEach( item -> {
                        strings.add(item.getId()+"");
                    });
                    list.add(strings);
                });
                //
                List<String> list1 = getDate(list);
                System.out.println(list1);
                list1.forEach(str -> {
                    Price price = new Price();
                    String[] strs = str.split("&");
                    int[] ints = new int[strs.length];
                    List<Integer> list2 = new ArrayList<>();
                    if (strs.length > 1) {
                        for (int i = 0; i < strs.length; i ++) {
                            ints[i] = Integer.parseInt(strs[i]);
                        }
                    }
                    Arrays.sort(ints);
                    String itemSet = ints[0] + "";
                    for (int i = 1; i < ints.length; i ++) {
                        itemSet += "&" + ints[i];
                    }
                    price.setPrice(-1.0);
                    price.setItemSet(itemSet);
                    price.setBid(b.getBid());
                    price.setGid(goods.getGid());
                    priceMapper.insert(price);
                });
            });


            //System.out.println(goodsList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return goodsList;



    }

    @Override
    public String getPriceList(String gid) throws IOException {
        List<Price> priceList = foo(gid);
        Price price = priceList.get(0);
        Goods goods = goodsMapper.selectById(gid);

        List<String> criterionList = new ArrayList<>();
        String[] itemStrs = price.getItemSet().split("&");
        for (String str : itemStrs) {
            criterionList.add(criterionMapper.queryInfoByItemId(Integer.parseInt(str)));
        }
        String filename = System.currentTimeMillis() +"" + new Random().nextInt(100000) + ".xlsx";
        File  f= new File("D:\\app\\nginx-1.17.4\\pawnshop\\assets\\excel/"+filename);
        if(!f.exists()){
            f.createNewFile();
        }
        Workbook workbook = new XSSFWorkbook();
        //lockstyle.setFillPattern();
        Sheet sheet = workbook.createSheet();
        int criterionsNum = criterionList.size();
        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue(goods.getName());
        for (int i = 0; i < criterionsNum; i ++) {
            row.createCell(i+1).setCellValue(criterionList.get(i));
        }
        row.createCell(criterionsNum + 1).setCellValue("价格设置");
        for (int i = 0; i < priceList.size(); i ++) {
            Price p = priceList.get(i);
            Row row1 = sheet.createRow(i + 1);
            Cell cell = row1.createCell(0);
            cell.setCellValue(p.getPid());
            List<String> list = p.getInfoSet();
            for (int j = 0; j < criterionsNum; j ++) {
                Cell cell1 = row1.createCell(j + 1);
                cell1.setCellValue(list.get(j));
            }
            row1.createCell(criterionsNum + 1).setCellValue(p.getPrice() == -1 ? "" : p.getPrice()+"");
        }

        FileOutputStream fos = new FileOutputStream(f);;
        workbook.write(fos);
        fos.flush();
        fos.close();
        workbook.close();
        return filename;
    }

    @Override
    @Transactional
    public Result setPrice(File f, String gid) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum();
        QueryWrapper<Criterion> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        int cols = criterionMapper.selectCount(wrapper);
        for (int i = 1; i <= rows; i ++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(cols + 1);
            if (cell != null) {
                Double priceStr = cell.getNumericCellValue();
                priceMapper.setPrice((int) row.getCell(0).getNumericCellValue(), priceStr);
            }
        }

        workbook.close();
        fis.close();
        Goods goods = goodsMapper.selectById(gid);

        if (goods != null && goods.getGoodsPhoto() != null && !goods.getGoodsPhoto().trim().equals("")) {
            setStatus(gid, GoodsStatus.TO_BE_REVIEW);
        }

        return new Result(true);
    }

    @Override
    public int setGoodsPhoto(String gid, String name) {
        QueryWrapper<Price> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        wrapper.eq("price" , -1);
        if (priceMapper.selectCount(wrapper) == 0) {
            setStatus(gid, GoodsStatus.TO_BE_REVIEW);
        }
        return goodsMapper.setPhoto(gid, name);
    }

    @Override
    public Business getGoodsByPhone(String principal) {
        QueryWrapper<Business> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", principal);
        return businessMapper.selectOne(wrapper);
    }

    @Override
    public PageInfo<Goods> getGoods(Goods goods, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return new PageInfo<>(goodsMapper.queryGoodsByAnd(goods));
    }

    @Override
    public Result setStatus(String gid, Integer status) {
        if (goodsMapper.setStatus(gid, status) == 1) {
            return new Result(true);
        }
        return null;
    }

    /**
     * 拒绝上架
     * @param gid
     */
    @Override
    public void refusePublic(String gid) {
        Goods goods = goodsMapper.selectById(gid);
        Business business = businessMapper.selectById(goods.getGid());
        String msg = business.getEmail() + "&审核结果&您的商品:" + goods.getName() + " 信息不合规范,上架审核被拒绝";
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,RabbitmqConfig.INFORM_EMAIL, msg);
    }

    @Override
    public PageInfo<Goods> getGoodsAdmin(Goods goods, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Goods> goodsList = goodsMapper.queryGoodsByAnd(goods);
        goodsList.forEach(g -> {
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("gid", g.getGid());
            g.setOrderSum(orderMapper.selectCount(wrapper));
        });
        return new PageInfo<>(goodsList);
    }

    @Override
    public Result getMaxOrderNumGoods() {
        Result result = new Result();
        List<GoodsDto> list = orderMapper.getMaxOrderNumGoods();
        list.forEach(goodsDto -> {
            goodsDto.setGoods(goodsMapper.selectById(goodsDto.getGid()));
            goodsDto.setMaxPrice(priceMapper.getMaxPriceByGid(goodsDto.getGid()));
        });
        result.setTag(true);
        result.setObj(list);
        return result;
    }

    @Override
    public Result getGoodsByName(String name) {
        Result result = new Result();
        List<GoodsDto> list = orderMapper.getGoodsByName(name);
        list.forEach(goodsDto -> {
            goodsDto.setGoods(goodsMapper.selectById(goodsDto.getGid()));
            goodsDto.setMaxPrice(priceMapper.getMaxPriceByGid(goodsDto.getGid()));
        });
        result.setTag(true);
        result.setObj(list);
        return result;
    }

    @Override
    public Result getGoodsDetailByGid(String gid) {
        Result result = new Result();
        Goods goods = goodsMapper.selectById(gid);
        QueryWrapper<Criterion> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        List<Criterion> criterionList = criterionMapper.selectList(wrapper);
        criterionList.forEach(criterion -> {

            QueryWrapper<CriterionItem> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("cid", criterion.getCid());
            criterion.setItemList(itemMapper.selectList(wrapper1));
        });
        goods.setCriterionList(criterionList);
        QueryWrapper<Order> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("gid", gid);
        int num = orderMapper.selectCount(wrapper1);
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setNum(num);
        goodsDto.setGoods(goods);
        result.setTag(true);
        result.setObj(goodsDto);
        return result;
    }


    private List<Price> foo (String gid) {
        Map<String, String> map = new HashMap<>();
        QueryWrapper<CriterionItem> wrapper = new QueryWrapper<>();
        wrapper.eq("gid", gid);
        List<CriterionItem> itemList = itemMapper.selectList(wrapper);
        itemList.forEach(item -> {
            map.put(item.getId()+"", item.getDescInfo());
        });
        QueryWrapper<Price> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("gid", gid);
        List<Price> priceList = priceMapper.selectList(wrapper2);
        priceList.forEach(price -> {
            String itemSet = price.getItemSet();
            List<String> list = new ArrayList<>();
            String[] itemStrs = itemSet.split("&");
            for (String str : itemStrs) {
                list.add(map.get(str));
            }
            price.setInfoSet(list);

        });
        return priceList;
    }


    /**
     * 根据商品的各个指标搭配指标项
     * @param list
     * @return
     */
    private List<String> getDate(List<List<String>> list) {
        List<String> strings = new ArrayList<>();
        if (list.size() == 1) {
            List<String> list1 = list.get(0);
            for (int i = 0; i < list1.size() ; i ++ ){
                strings.add(list.get(i) + "");
            }
            return strings;
        } else if (list.size() == 2) {
            List<String> list1 = list.get(0);
            List<String> list2 = list.get(1);

            for (int i = 0; i < list1.size() ; i ++ ){

                for (int j = 0; j < list2.size() ; j ++) {
                    strings.add(list1.get(i) + "&" + list2.get(j) );
                }
            }
            return strings;
        } else {
            int size = list.size();
            List<String> list1 = list.get(size-1);
            List<String> list2 = list.get(size-2);
            for (int i = 0; i < list1.size() ; i ++ ){
                for (int j = 0; j < list2.size() ; j ++) {
                    strings.add(list1.get(i) + "&" + list2.get(j));
                }
            }
            List<List<String>> list3 = new ArrayList<>(list);
            list3.remove(size-1);
            list3.remove(size-2);
            list3.add(strings);
            list3.set(list3.size() - 1, list3.get(0));
            list3.set(0, strings);
            return getDate(list3);
        }


    }

    /**
     * 查询商品价格是否全部设置
     * @param gid
     * @return
     */
    private boolean isAllInfo (String gid) {

        Goods goods = goodsMapper.selectById(gid);

        if (goods != null && goods.getGoodsPhoto() != null && !goods.getGoodsPhoto().trim().equals("")) {
            QueryWrapper<Price> wrapper = new QueryWrapper<>();
            wrapper.eq("gid", gid);
            wrapper.ne("price" , -1);
            if (priceMapper.selectCount(wrapper) == 0) {
                return true;
            }
        }
        return false;
    }
}
