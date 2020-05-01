package cn.ckh2019.pawnshop.service.user.service;

import cn.ckh2019.pawnshop.commons.bean.Result;
import cn.ckh2019.pawnshop.commons.mapper.GoodsMapper;
import cn.ckh2019.pawnshop.commons.mapper.UserMapper;
import cn.ckh2019.pawnshop.commons.model.dto.RegisterDto;
import cn.ckh2019.pawnshop.commons.model.pojo.Goods;
import cn.ckh2019.pawnshop.commons.model.pojo.User;
import cn.ckh2019.pawnshop.service.user.config.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Kaihong
 * 2020-02-21 14:20
 */
@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Result sendRegisterCheckMeg(String phone) {
        Result result = new Result();
        User user = userMapper.getUserByEmailOrPhone(phone);
        if (user != null) {
            result.setTag(false);
            result.setMsg("该手机已被注册");
        } else if ( redisTemplate.opsForValue().get(phone) != null){
            result.setTag(false);
            result.setMsg("请稍后再发送");
        } else {
            String randomCode = getRandomCode();
            redisTemplate.opsForValue().set(phone, randomCode, 60, TimeUnit.SECONDS);
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,RabbitmqConfig.INFORM_SMS,phone+"&"+randomCode);
            result.setTag(true);
        }
        return result;
    }

    @Override
    public Result register(RegisterDto registerDto) {
        Result result = new Result();
        if (registerDto != null
                && registerDto.getCheckCode()!= null
                && registerDto.getCheckCode().equals(redisTemplate.opsForValue().get(registerDto.getPhone()))){
            User user = new User();
            user.setPhone(registerDto.getPhone());
            user.setEmail(registerDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            if (userMapper.insert(user) == 1) {
                result.setTag(true);
            }
        }

        return result;
    }

    @Override
    public Result sendResetMeg(String principal, Integer method) {
        Result result = new Result();
        String randomCode = getRandomCode();
        if (method == 0 && userMapper.getUserByPhone(principal) != null ) {
            redisTemplate.opsForValue().set(principal, randomCode, 60, TimeUnit.SECONDS);
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,RabbitmqConfig.INFORM_SMS,principal+"&"+randomCode);
            result.setTag(true);
        } else if (method == 1 && userMapper.getUserByPhone(principal) != null) {
            redisTemplate.opsForValue().set(principal, randomCode, 60, TimeUnit.SECONDS);
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,RabbitmqConfig.INFORM_EMAIL,principal+"&"+randomCode);
            result.setTag(true);

        }
        return result;
    }

    @Override
    public Result resetPwd(String principal, Integer method, String checkCode, String password) {
        Result result = new Result();
        if (checkCode != null
                && principal != null
                && checkCode.equals(redisTemplate.opsForValue().get(principal))){
            if (userMapper.updateByEmailOrPhone(passwordEncoder.encode(password), principal) == 1) {
                result.setTag(true);
            }
        }
        return result;
    }

    private String getRandomCode () {
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        return result;
    }


    public List<Goods> searchGoods(String key) {
        return goodsMapper.searchGoods(key);
    }
}
