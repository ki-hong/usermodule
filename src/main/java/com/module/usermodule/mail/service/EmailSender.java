package com.module.usermodule.mail.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    private final JavaMailSender mailSender;
    private final SimpleEmailSendable emailSendable;

    public EmailSender(JavaMailSender mailSender, SimpleEmailSendable emailSendable) {
        this.mailSender = mailSender;
        this.emailSendable = emailSendable;
    }
    private String subject = "[유저 인증 테이스] 이메일의 링크를 확인해주세요";
    private String messagePrefix = "인증 링크입니다. 링크를 눌러주세요. http://localhost:8080/users/activate/";


    public void sendEmail(String[] to){
        String message = messagePrefix+to[0];
        emailSendable.send(to,subject,message);
    }

}
