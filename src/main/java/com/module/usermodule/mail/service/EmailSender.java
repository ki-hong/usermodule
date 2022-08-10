package com.module.usermodule.mail.service;

import com.module.usermodule.util.AES256Util;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Service
public class EmailSender {
    private final JavaMailSender mailSender;
    private final SimpleEmailSendable emailSendable;
    private final AES256Util aes256Util;

    public EmailSender(JavaMailSender mailSender, SimpleEmailSendable emailSendable, AES256Util aes256Util) {
        this.mailSender = mailSender;
        this.emailSendable = emailSendable;
        this.aes256Util = aes256Util;
    }

    private String subject = "[유저 인증 테이스] 이메일의 링크를 확인해주세요";
    private String massagePrefix = "인증 링크입니다. 링크를 눌러주세요. http://localhost:8080/users/activate/";

    private String massageSuffix="";

    public void sendEmail(String[] to) throws GeneralSecurityException, UnsupportedEncodingException {
        massageSuffix= aes256Util.encrypt(to[0]);
        String message = massagePrefix+massageSuffix;
        emailSendable.send(to,subject,message);
    }

}
