package com.module.usermodule.user.entity;

import com.module.usermodule.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends Auditable {

    @Id
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String nickname;



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

    @Builder
    public Users(Long id, String email, String name, String phone, String nickname) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.nickname = nickname;
    }
}
