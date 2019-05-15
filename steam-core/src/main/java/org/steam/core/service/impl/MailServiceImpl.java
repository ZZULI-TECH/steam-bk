package org.steam.core.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.steam.common.exception.ServiceException;
import org.steam.core.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.address}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) throws ServiceException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            log.info("邮件发送: {}成功！", to);
        } catch (Exception e) {
            log.info("邮件发送: {}失败，原因: ", to, e.getCause());
            throw new ServiceException(1101L, "邮件发送失败");
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) throws ServiceException {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            log.info("html邮件发送: {}成功", to);
        } catch (MessagingException e) {
            log.info("html邮件发送: {}失败，原因: ", to, e.getCause());
            throw new ServiceException(1102L, "html邮件发送失败");
        }

    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws ServiceException {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            log.info("带附件的邮件发送: {}", to);
        } catch (MessagingException e) {
            log.error("带附件的邮件时发送：{}失败, 原因：", to, e.getCause());
            throw new ServiceException(1103L, "发送带附件的邮件失败");
        }
    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) throws ServiceException {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            log.info("嵌入静态资源的邮件发送: {}成功", to);
        } catch (MessagingException e) {
            log.error("嵌入静态资源的邮件发送：{}失败，原因：", to, e.getCause());
            throw new ServiceException(1104L, "发送嵌入静态资源的邮件失败");
        }
    }
}
