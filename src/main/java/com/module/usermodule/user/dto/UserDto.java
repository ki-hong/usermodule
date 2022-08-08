package com.module.usermodule.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @Builder
    public class UserPostDto{
        private String email;
        private String password;
        private String name;
        private String phone;
        private String nickname;
    }

    @Getter
    @Builder
    public class UserResponseDto{
        private String email;
        private String name;
        private String phone;
        private String nickname;
    }
}
