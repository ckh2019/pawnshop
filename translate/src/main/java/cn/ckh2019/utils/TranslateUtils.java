package cn.ckh2019.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Chen Kaihong
 * 2019-09-28 22:35
 */
public class TranslateUtils {
    private static final String APP_ID = "20190927000337632";
    private static final String SECURITY_KEY = "E0y1netwErkhQ7DD17jr";
    private static TransApi transApi = new TransApi(APP_ID, SECURITY_KEY);
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public  String translate(String content, String to){
        String result = transApi.getTransResult(content, "auto", to);
        System.out.println(Main.unicodetoString("\\u9759\\u304b\\u306a"));
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray array = (JSONArray)jsonObject.get("trans_result");
        JSONObject object = (JSONObject) array.get(0);
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DbUtils.insert(jsonObject.getString("from"), jsonObject.getString("to"), object.getString("src"), object.getString("dst"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        return object.getString("dst");
    }

}
