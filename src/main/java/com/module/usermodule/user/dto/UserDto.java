package com.module.usermodule.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserPostDto{
        private String email;
        private String password;
        private String name;
        private String phone;
        private String nickname;

        @Builder
        public UserPostDto(String email, String password, String name, String phone, String nickname) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.phone = phone;
            this.nickname = nickname;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserResponseDto{
        private String email;
        private String name;
        private String phone;
        private String nickname;

        @Builder
        public UserResponseDto(String email, String name, String phone, String nickname) {
            this.email = email;
            this.name = name;
            this.phone = phone;
            this.nickname = nickname;
        }
    }
}
