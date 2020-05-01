package cn.ckh2019.pawnshop.service.message.handle;

import cn.ckh2019.pawnshop.service.message.config.RabbitmqConfig;
import cn.ckh2019.pawnshop.service.message.service.MailService;
import cn.ckh2019.pawnshop.service.message.service.SmsService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Chen Kaihong
 * 2020-02-21 15:23
 */
@Component
public class ReceiveHandler {

    @Autowired
    MailService mailService;

    @Autowired
    SmsService smsService;

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
    public void receiveEmail(String msg, Message message, Channel channel) {
        String[] strings = msg.split("&");
        mailService.sendSimpleMail(strings[0], strings[1], strings[2]);
    }

    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
    public void receiveSms(String msg, Message message, Channel channel) {
        String[] strings = msg.split("&");
        smsService.sendMessage(strings[0], strings[1]);
    }

}
