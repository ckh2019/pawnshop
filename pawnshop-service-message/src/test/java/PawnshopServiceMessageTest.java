/**
 * @author Chen Kaihong
 * 2020-02-10 22:04
 */

import cn.ckh2019.pawnshop.service.message.PawnshopServiceMessageApplication;
import cn.ckh2019.pawnshop.service.message.service.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PawnshopServiceMessageApplication.class)
public class PawnshopServiceMessageTest {

    @Autowired
    private SmsService messageService;

    @Test
    public void testSimpleMail() throws Exception {

        messageService.sendMessage("15377187316", "108909");

    }
}
