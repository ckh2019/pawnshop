import cn.ckh2019.pawnshop.service.user.PawnshopServiceUserApplication;
import cn.ckh2019.pawnshop.service.user.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Chen Kaihong
 * 2020-02-21 15:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PawnshopServiceUserApplication.class)
public class MyTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test() {
        for (int i=0;i<5;i++){
            String message = "sms email inform to ckh"+i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);
            System.out.println("Send Message is:'" + message + "'");
        }

    }

    @Test
    public void test2() {
        redisTemplate.opsForValue().set("test1","1234",5, TimeUnit.SECONDS);

        long start = System.currentTimeMillis();
        while (true) {
            Object o = redisTemplate.opsForValue().get("test1");
            System.out.println(o);
            if (o == null) {
                System.out.println(System.currentTimeMillis() - start);
                break;
            }


        }
    }
}
