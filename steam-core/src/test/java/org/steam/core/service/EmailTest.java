package org.steam.core.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.steam.common.exception.ServiceException;

/**
 * @author mingshan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailTest {

    @Autowired
    private MailService mailService;

    public void testSendEmail() {
        try {
            mailService.sendHtmlMail("499445428@qq.com", "Steam邮箱验证", "您的验证码为: 2345，5分钟内有效");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
