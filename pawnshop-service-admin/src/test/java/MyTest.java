import cn.ckh2019.pawnshop.commons.mapper.BusinessMapper;
import cn.ckh2019.pawnshop.commons.model.pojo.Business;
import cn.ckh2019.pawnshop.service.admin.PawnshopServiceAdminApplication;
import cn.ckh2019.pawnshop.service.admin.service.BusinessService;
//import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author Chen Kaihong
 * 2020-02-21 15:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PawnshopServiceAdminApplication.class)
public class MyTest {

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessMapper businessMapper;


    @Test
    public void test() {
        Business business = new Business();
        business.setStatus(0);
        //PageInfo<Business> info = businessService.getBusiness(business, 1, 2);
        //System.out.println(businessService.getBusiness(business));
    }


}
