package com.sh.utils;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 27300
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SendMail {
    @Resource
    private JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String sendMailer;
    
    
    /**
     * 发送纯文本邮件
     * @param to
     * @param subject
     * @param text
     */
    public void sendTextMailMessage(String to,String subject,String text){
        if (StrUtil.isBlankIfStr(to)){
            return;
        }
        try {
            //true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人  1或多个
            mimeMessageHelper.setTo(to.split(","));
            //邮件主题
            mimeMessageHelper.setSubject(subject);
            //邮件内容
            mimeMessageHelper.setText(text);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());
            
            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
			log.info("发送邮件成功：{}->{}", sendMailer, to);
            
        } catch (MessagingException e) {
            e.printStackTrace();
			log.error("{}->{} ,发送邮件失败：{}", sendMailer, to, e.getMessage());
        }
    }
    
    
}
