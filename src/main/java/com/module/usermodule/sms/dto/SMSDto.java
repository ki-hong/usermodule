package com.module.usermodule.sms.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

public class SMSDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MessagesRequestDto {
        private String to;
        private String content;

        @Builder
        public MessagesRequestDto(String to, String content) {
            this.to = to;
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SMSRequestDto {
        private String type;
        private String contentType;
        private String countryCode;
        private String from;
        private String content;
        private List<SMSDto.MessagesRequestDto> messages;

        @Builder
        public SMSRequestDto(String type, String contentType, String countryCode, String from, String content, List<SMSDto.MessagesRequestDto> messages) {
            this.type = type;
            this.contentType = contentType;
            this.countryCode = countryCode;
            this.from = from;
            this.content = content;
            this.messages = messages;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SendSMSResponseDto {
        private String statusCode;
        private String statusName;
        private String requestId;
        private Timestamp requestTime;

        @Builder
        public SendSMSResponseDto(String statusCode, String statusName, String requestId, Timestamp requestTime) {
            this.statusCode = statusCode;
            this.statusName = statusName;
            this.requestId = requestId;
            this.requestTime = requestTime;
        }
    }
}
