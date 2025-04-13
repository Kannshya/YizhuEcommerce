package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.system.tools.SampleMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 邮件服务
 */
@Component
public class EmailService {
    @Autowired
    private RedisCache redisCache;

    /**
     * 生成6位随机验证码
     */
    private String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    /**
     * 发送邮箱验证码
     *
     * @param email 邮箱地址
     * @return 发送结果
     */
    public String sendVerificationCode(String email) {
        String code = generateVerificationCode();
        String verifyKey = CacheConstants.EMAIL_CODE_KEY + email;

        try {
            // 发送邮件
            String content = String.format("您的验证码是：%s，有效期5分钟，请勿泄露给他人。", code);
            SampleMail.sendEmail(email, "注册验证码", content);

            // 将验证码存入Redis，设置5分钟过期
            redisCache.setCacheObject(verifyKey, code, 5, TimeUnit.MINUTES);
            return "验证码发送成功";
        } catch (MessagingException | UnsupportedEncodingException e) {
            return "验证码发送失败：" + e.getMessage();
        }
    }

    /**
     * 验证邮箱验证码
     *
     * @param email 邮箱地址
     * @param code  验证码
     * @return 验证结果
     */
    public boolean verifyCode(String email, String code) {
        String verifyKey = CacheConstants.EMAIL_CODE_KEY + email;
        String cachedCode = redisCache.getCacheObject(verifyKey);
        
        if (cachedCode == null) {
            return false;
        }
        
        boolean result = cachedCode.equals(code);
        if (result) {
            redisCache.deleteObject(verifyKey);
        }
        return result;
    }
}