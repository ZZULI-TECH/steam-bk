package org.steam.core.service;

import org.steam.common.exception.ServiceException;

/**
 * 邮件服务
 *
 */
public interface MailService {
    /**
     * 发送简单邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject, String content) throws ServiceException;

    /**
     * 发送html邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    void sendHtmlMail(String to, String subject, String content) throws ServiceException;

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath) throws ServiceException;

    /**
     * 发送带静态资源的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId)
            throws ServiceException;
}
