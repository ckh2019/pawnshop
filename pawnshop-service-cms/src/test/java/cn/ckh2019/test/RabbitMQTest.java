/*
package cn.ckh2019.test;

import cn.ckh2019.pawnshop.service.cms.PawnshopServiceCmsApplication;
import cn.ckh2019.pawnshop.service.cms.config.RabbitMQConfig;
import com.rabbitmq.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

*/
/**
 * @author Chen Kaihong
 * 2019-10-31 15:02
 *//*

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PawnshopServiceCmsApplication.class)
public class RabbitMQTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    static final String QUEUE = "queue-01";

    static final String EXCHANGE = "fanout_exchange";

    public static final String EXCHANGE_FANOUT_INFORM = "exchange_fanout_inform";
    public static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    public static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    public static final String ROUTINGKEY_EMAIL = "routingkey_email";
    public static final String ROUTINGKEY_SMS = "routingkey_sms";

    private  Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("129.211.17.8");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

    */
/**
     * Producer 向队列发送5条消息
     *//*

    @Test
    public void  test1() {

        Connection connection = getConnection();
        Channel channel = null;
        try {
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE, true, false, false, null);
            for (int i = 0; i < 10; i ++) {
                channel.basicPublish("",QUEUE,null,("ckh" + i).getBytes());
                System.out.println("发送完成 : ckh" + i);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    */
/**
     * 定义了3个Consumer,同时监听同一个队列
     *//*

    @Test
    public void test2() {
        //
        Connection connection1= getConnection();
        Connection connection2 = getConnection();
        Connection connection3 = getConnection();
        try {
            Channel channel1 = connection1.createChannel();
            Channel channel2 = connection2.createChannel();
            Channel channel3 = connection3.createChannel();
            channel1.queueDeclare(QUEUE, true, false, false, null);
            channel2.queueDeclare(QUEUE, true, false, false, null);
            channel3.queueDeclare(QUEUE, true, false, false, null);

            while (true) {
                channel1.basicConsume(QUEUE,true, new DefaultConsumer(channel1){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String msg = new String(body,"utf-8");
                        System.out.println("msg1 =======> " + msg);
                    }
                });
                channel2.basicConsume(QUEUE,true, new DefaultConsumer(channel1){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String msg = new String(body,"utf-8");
                        System.out.println("msg2 =======> " + msg);
                    }
                });
                channel3.basicConsume(QUEUE,true, new DefaultConsumer(channel1){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String msg = new String(body,"utf-8");
                        System.out.println("msg3 =======> " + msg);
                    }
                });
            }


        } catch (Exception e){

        } finally {

        }

    }

    */
/**
     * publish
     *//*

    @Test
    public void publish () throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        */
/*channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
        channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);*//*

        channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
        */
/*channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_FANOUT_INFORM, "");
        channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_FANOUT_INFORM, "");*//*


        for (int i = 0; i < 20; i ++) {
            String msg = "hello " + i;
            channel.basicPublish(EXCHANGE_FANOUT_INFORM, "", null, msg.getBytes());
            System.out.println(msg);
        }
        channel.close();
        connection.close();

    }

    */
/**
     * email
     *//*

    @Test
    public void email () throws IOException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
        channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
        channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_FANOUT_INFORM, "");

        while (true) {
            channel.basicConsume(QUEUE_INFORM_EMAIL, true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body,"utf-8");
                    System.out.println("email =======> " + msg);
                }
            });
        }

    }

    */
/**
     * sms
     *//*

    @Test
    public void sms () throws IOException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);
        channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
        channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_FANOUT_INFORM, "");

        while (true) {
            channel.basicConsume(QUEUE_INFORM_SMS, true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body,"utf-8");
                    System.out.println("sms =======> " + msg);
                }
            });
        }

    }


    @Test
    public void sendTopic () {
        //rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPICS_INFORM, );

    }


    private static final String EXCHANGE_ROUTING_INFORM = "exchange_routing_inform";




    */
/**
     * 路由发布
     *//*

    @Test
    public void test3 () {
        Connection connection = getConnection();
        try {
            Channel channel = connection.createChannel();
            //channel.exchangeDeclare(EXCHANGE_ROUTING_INFORM, BuiltinExchangeType.DIRECT);
            channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTING_INFORM, ROUTINGKEY_EMAIL);
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, ROUTINGKEY_SMS);
            for (int i = 0; i < 20; i ++) {
                String msg = "hello ckh " + i;
                channel.basicPublish(EXCHANGE_FANOUT_INFORM, ROUTINGKEY_SMS, null, msg.getBytes());
                System.out.println(msg);
            }
            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    */
/**
     * 路由消费 email
     *//*

    @Test
    public void test4 () throws IOException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        // channel.queueDeclare(QUEUE_INFORM_EMAIL, false, false, false, null);
        channel.exchangeDeclare(EXCHANGE_TOPIC_INFORM, BuiltinExchangeType.TOPIC);
        channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_TOPIC_INFORM, "routingkey.#.email.#");

        while (true) {
            channel.basicConsume(QUEUE_INFORM_EMAIL, true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body,"utf-8");
                    System.out.println("email routingkey =======> " + msg);
                }
            });
        }
    }

    */
/**
     * 路由消费 sms
     *//*

    @Test
    public void test5 () throws IOException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        //channel.queueDeclare(QUEUE_INFORM_SMS, false, false, false, null);
        //channel.exchangeDeclare(EXCHANGE_ROUTING_INFORM, BuiltinExchangeType.DIRECT);
        channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_TOPIC_INFORM, "routingkey.#.sms.#");

        while (true) {
            channel.basicConsume(QUEUE_INFORM_SMS, true, new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body,"utf-8");
                    System.out.println("sms routingkey =======> " + msg);
                }
            });
        }
    }

    private static final String EXCHANGE_TOPIC_INFORM = "exchange_topic_inform";

    private  static final String  ROUTINGKEY_ALL = "routingkey.#.email.#";

    */
/**
     * 路由发布
     *//*

    @Test
    public void test6 () {
        Connection connection = getConnection();
        try {
            Channel channel = connection.createChannel();
            //channel.exchangeDeclare(EXCHANGE_ROUTING_INFORM, BuiltinExchangeType.DIRECT);
            //channel.queueBind(QUEUE_INFORM_EMAIL, EXCHANGE_ROUTING_INFORM, ROUTINGKEY_EMAIL);
            //channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_ROUTING_INFORM, ROUTINGKEY_SMS);
            for (int i = 0; i < 3; i ++) {
                String msg = "hello sms " + i;
                channel.basicPublish(EXCHANGE_TOPIC_INFORM, "routingkey.sms", null, msg.getBytes());
                System.out.println(msg);
            }
            for (int i = 0; i < 3; i ++) {
                String msg = "hello sms.email " + i;
                channel.basicPublish(EXCHANGE_TOPIC_INFORM, "routingkey.sms.email", null, msg.getBytes());
                System.out.println(msg);
            }
            channel.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}
*/
