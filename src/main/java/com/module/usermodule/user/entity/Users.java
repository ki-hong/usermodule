package com.module.usermodule.user.entity;

import com.module.usermodule.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends Auditable {

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private Role role=Role.ROLE_USER;
    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;


    public enum Status{

        INACTIVE(1,"인증 전"),
        ACTIVE(2,"활동 중"),
        DELETE(3,"삭제");

        private int stepNumber;

        private String userDescription;

        Status(int stepNumber, String userDescription) {
            this.stepNumber = stepNumber;
            this.userDescription = userDescription;
        }
    }

    public enum Role{
        ROLE_USER("일반유저"),
        ROLE_ADMIN("관리자");

        private String roleDescription;

        Role(String roleDescription) {
            this.roleDescription = roleDescription;
        }
    }

    @Builder
    public Users(Long id, String email, String password, String name, String phone, String nickname, Status status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
        this.status = status;
    }




    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
