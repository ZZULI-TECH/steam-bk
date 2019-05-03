package org.steam.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.steam.common.cache.RedisCache;
import org.steam.common.exception.ParameterException;
import org.steam.common.exception.ServiceException;
import org.steam.core.service.MailService;
import org.steam.core.service.VerificationCodeService;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author mingshan
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private static final char[] CHARS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private static final String REGISTER_SUBJECT = "Steam邮箱验证";
    private static final String CODE_PREFIX = "secode";

    @Autowired
    private MailService mailService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void sendSeccode(String email) throws ServiceException {
        if (StringUtils.isEmpty(email)) {
            throw new ParameterException("Email is empty.");
        }

        String code = generateVerifyCode(4);
        mailService.sendHtmlMail(email, REGISTER_SUBJECT, "您的验证码为: " + code + "，5分钟内有效");
        // 存入redis
        redisCache.setEx(CODE_PREFIX + email, code, 5, TimeUnit.MINUTES);
    }

    @Override
    public void verfiySecCode(String email, String seccode) throws ServiceException {
        String code = redisCache.get(CODE_PREFIX + email);
        if (StringUtils.isEmpty(code) || !code.equals(seccode)) {
            throw new ServiceException(1003L, "验证码验证失败");
        }
    }

    /**
     * 使用指定源生成验证码
     *
     * @param verifySize 验证码长度
     * @return 生成的验证码
     */
    private static String generateVerifyCode(int verifySize) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final char[] sources = CHARS;
        int codesLen = sources.length;
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources[random.nextInt(codesLen)]);
        }
        return verifyCode.toString();
    }
}
