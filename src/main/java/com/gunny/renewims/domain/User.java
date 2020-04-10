package com.gunny.renewims.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Builder
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false, length = 20)
    private String userId;

    @Size(min = 3, max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @Size(min = 6, max = 20)
    @Column(nullable = false, length = 20)
    private String password;

    public void update(User loginUser, User target) {
        if (!matchUserId(loginUser.getUserId())) {
            throw new IllegalArgumentException("로그인 유저와 다릅니다.");
        }

        if (!matchPassword(target)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        this.name = target.name;
    }

    private boolean matchUserId(String targetId) {
        return this.userId.equals(targetId);
    }

    private boolean matchPassword(User target) {
        return this.password.equals(target.password);
    }
}
