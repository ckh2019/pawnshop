package cn.ckh2019.pawnshop.service.message.service.impl;

import cn.ckh2019.pawnshop.service.message.service.MessageService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Chen Kaihong
 * 2020-02-14 17:02
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    /**
     *  短信应用 SDK AppID
     */
    public static final  int APP_ID = 1400315907; // SDK AppID 以1400开头
    /**
     *  短信应用 SDK AppKey
     */
    public static final String APP_KEY = "790990d98be6c5ca9fa6a801d6073053";
    // 需要发送短信的手机号码
    //public static final String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};
    /**
     * 短信模板 ID，需要在短信应用中申请
      */
    public static final int TEMPLATE_ID = 534591; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请
    /**
     * 签名
     */
    public static final String SMS_SIGN = "ckh2019";

    @Override
    public void sendMessage(String phone, String content) {

        try {
            String[] params = {"a gay"};
            SmsSingleSender ssender = new SmsSingleSender(APP_ID, APP_KEY);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    TEMPLATE_ID, params, SMS_SIGN, "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }

    }
}
