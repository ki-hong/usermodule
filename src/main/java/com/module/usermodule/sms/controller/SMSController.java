package com.module.usermodule.sms.controller;

import com.module.usermodule.sms.dto.SMSDto;
import com.module.usermodule.sms.service.SMSService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/Messages")
public class SMSController {

    private SMSService smsService;

    public SMSController(SMSService smsService) {
        this.smsService = smsService;
    }

    @GetMapping
    public ResponseEntity sendSMS(@RequestParam String phone) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {

        SMSDto.SendSMSResponseDto responseDto = smsService.sendSMS(phone);

        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

}
