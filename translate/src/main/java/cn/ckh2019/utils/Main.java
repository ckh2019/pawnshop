package cn.ckh2019.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * @author Chen Kaihong
 * 2019-09-27 9:36
 */
public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20190927000337632";
    private static final String SECURITY_KEY = "E0y1netwErkhQ7DD17jr";

    public static String unicodetoString(String unicode){
        if(unicode==null||"".equals(unicode)){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while((i=unicode.indexOf("\\u", pos)) != -1){
            sb.append(unicode.substring(pos, i));
            if(i+5 < unicode.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
            }
        }
        return sb.toString();
    }

    public void test() {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "创建一个可缓存线程池，如果线程池长度超过处理需要";
        //JSONObject.parse(api.getTransResult(query, "wyw", "zh"));
        String result = api.getTransResult(query, "auto", "wyw");
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray array = (JSONArray)jsonObject.get("trans_result");
        JSONObject object = (JSONObject) array.get(0);
        System.out.println(object.getString("dst"));
        array.get(0);
        //jsonObject.get("trans_result");
        System.out.println(unicodetoString(result));
        //return unicodetoString(object.getString("dst"));
        //System.out.println(api.getTransResult(query, "wyw", "zh"));

    }

    public void test2() {
        try {
            URL url = new URL("https://tool.runoob.com/compile.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "/");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.connect();
            String body = "code=public+class+HelloWorld+{public+static+void++++main(String+[]args)+{+System.out.println(111);System.out.println(111);System.out.println(111); ++++} }&" +
                    "token=4381fe197827ec87cbac9552f14ec62a&stdin=&language=8&fileext=java";
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(body);
            writer.close();
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //10MB的缓存
                byte[] buffer = new byte[10485760];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String jsonString = baos.toString();
                baos.close();
                is.close();
                System.out.println(jsonString);
                //转换成json数据处理
                // getHttpJson函数的后面的参数1，表示返回的是json数据，2表示http接口的数据在一个（）中的数据
                //JSONObject jsonArray = getJsonString(jsonString, 1);
                //return jsonArray; 云工具
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public JSONObject getJsonString(String str, int comefrom) throws Exception{
        JSONObject jo = null;
        if(comefrom==1){
            return new JSONObject(str);
        }else if(comefrom==2){
            int indexStart = 0;
            //字符处理
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='('){
                    indexStart = i;
                    break;
                }
            }
            String strNew = "";
            //分割字符串
            for(int i=indexStart+1;i<str.length()-1;i++){
                strNew += str.charAt(i);
            }
            return new JSONObject(strNew);
        }
        return jo;
    }*/


    public static void main(String[] args) {

        new Main().test();
        //new TranslateUtils().translate("第二天早上他到楼顶花园漫步时","wyw");
        //System.out.println(unicodetoString("\\u9759\\u304b\\u306a"));


    }

}
