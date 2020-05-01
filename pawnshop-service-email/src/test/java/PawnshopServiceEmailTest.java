/**
 * @author Chen Kaihong
 * 2020-02-10 22:04
 */

import cn.ckh2019.pawnshop.service.email.PawnshopServiceEmailApplication;
import cn.ckh2019.pawnshop.service.email.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PawnshopServiceEmailApplication.class)
public class PawnshopServiceEmailTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("3170865835@qq.com","这是一封简单邮件","大家好，这是我的第一封邮件！");
    }
}
