package com.iotek.hrmgr.service.impl;

import com.iotek.hrmgr.entity.MailUser;
import com.iotek.hrmgr.service.TrashService;
import com.iotek.hrmgr.utils.MailMessageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

@Service("TrashService")
public class TrashServiceImpl implements TrashService{

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    JavaMailSenderImpl mailSender;




    @Override
    @Async
    public void sendTrashMails(){
        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("通知");
        message.setText("明天来面试");
        message.setTo("275945185@qq.com","zhengxingao@live.cn");
        message.setFrom("2570945863@qq.com");*/
        int i = 1;
        while (i < 200) {
            System.out.println("发送中...");
            MailUser mailUser = new MailUser("垃圾", "1145020703@qq.com");



            String template = "*{垃圾}#{垃圾\n@{currentDate}}";
            SimpleMailMessage message = new MailMessageGenerator(mailUser, "2570945863@qq.com", template, null).genMessage();
            mailSender.send(message);mailSender.send(message);
            i++;
            System.out.println("已发送");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
            }
        }
    }

}