package cn.ckh2019.pawnshop.service.cms.controller;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.util.AesCbcUtil;
import cn.ckh2019.pawnshop.commons.util.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen Kaihong
 * 2019-11-04 15:01
 */

public class TestController {

    @RequestMapping("/getOpenId")
    public Result getOpenID (String code) {
        Result result = new Result();
        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            result.setMsg("code不能为空");
        }
        String wxspAppid = "wx14221ef9c83dfc20";
        String wxspSecret = "88cad5a68695f38fa7e3e8f593ca55d1";
        // 授权
        String grant_type = "authorization_code";
        //1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）
        System.out.println(sr);
        JSONObject json = JSONObject.parseObject(sr);
        // 获取会话密钥（session_key）
        String session_key = (String) json.get("session_key");
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        System.out.println(json);
        result.setObj(sr);
        //对encryptedData加密数据进行AES解密
        /*try {
            String resultStr = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != resultStr && resultStr.length() > 0) {
                result.setTag(true);
                JSONObject userInfoJSON = JSONObject.parseObject(resultStr);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                // 解密unionId & openId;

                userInfo.put("unionId", userInfoJSON.get("unionId"));
                result.setObj(userInfo);
            } else {
                result.setMsg("解密失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return result;
    }
}
