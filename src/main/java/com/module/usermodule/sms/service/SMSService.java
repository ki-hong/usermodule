package com.module.usermodule.sms.service;

import com.module.usermodule.sms.dto.SMSDto;
import com.module.usermodule.sms.mapper.SMSMapper;
import lombok.Getter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Getter
@Service
public class SMSService {

    private SMSMapper smsMapper;


    public SMSService(SMSMapper smsMapper) {
        this.smsMapper = smsMapper;
    }

    public SMSDto.SendSMSResponseDto sendSMS(String phone) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
        Long time = System.currentTimeMillis();
        List<SMSDto.MessagesRequestDto> messages = new ArrayList<>();
        String content = "[유저 인증 테스트] 인증번호 ["+createNum()+"]를 입력해주세요";
        messages.add(SMSDto.MessagesRequestDto.builder().to(phone).content(content).build());
        SMSDto.SMSRequestDto smsRequestDto = SMSDto.SMSRequestDto.builder()
                                                                    .type("SMS")
                                                                    .contentType("COMM")
                                                                    .countryCode("82")
                                                                    .from(System.getenv("myPhone"))
                                                                    .content("MangoLtd")
                                                                    .messages(messages)
                                                                    .build();
        String requestBody =smsMapper.toString(smsRequestDto);
        System.out.println(requestBody);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", System.getenv("accessKey"));
        String signature = makeSignature(time);
        headers.set("x-ncp-apigw-signature-v2", signature);

        HttpEntity<String> body = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        SMSDto.SendSMSResponseDto sendSMSResponseDto = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+System.getenv("serviceId")+"/messages"), body, SMSDto.SendSMSResponseDto.class);

        return sendSMSResponseDto;

    }


    public String makeSignature(Long time) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {


        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "POST";					// method
        String url = "/sms/v2/services/"+System.getenv("serviceId")+"/messages";	// url (include query string)
        String timestamp = time.toString();     // current timestamp (epoch)
        String accessKey = System.getenv("accessKey");			// access key id (from portal or Sub Account)
        String secretKey = System.getenv("secretKey");

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();
        System.out.println("secretKey = " + secretKey);
        System.out.println("accessKey = " + accessKey);
        System.out.println("serviceId" + System.getenv("serviceId"));
        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

    private String createNum(){
        String createNum = "";
        Random random = new Random();
        for(int i =0; i<6; i++){
            createNum+= String.valueOf(random.nextInt(9));
        }
        return createNum;
    }
}
