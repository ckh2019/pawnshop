package cn.ckh2019.pawnshop.service.message.service;

/**
 * @author Chen Kaihong
 * 2020-02-10 21:55
 */
public interface MailService {
    void sendSimpleMail(String to, String subject, String content);
}
