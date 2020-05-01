package test;

import cn.ckh2019.pawnshop.service.goods.PawnshopServiceGoodsApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author Chen Kaihong
 * 2019-10-29 21:48
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PawnshopServiceGoodsApplication.class)
public class RibbonTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void test01 () {
        String serviceId = "PAWNSHOP-SERVICE-CMS";
        ResponseEntity<Map> entity = restTemplate.getForEntity("http://" + serviceId + "/userTest", Map.class);
        Map map = entity.getBody();
        System.out.println(map);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    public void discovery () {
        List<String> list = discoveryClient.getServices();
        System.out.println("============> " + list);
        List<ServiceInstance> instances = discoveryClient.getInstances("PAWNSHOP-SERVICE-CMS");
        instances.forEach(instance -> {
            System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getUri());
        });

    }
}
