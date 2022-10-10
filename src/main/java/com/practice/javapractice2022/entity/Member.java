package com.practice.javapractice2022.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter // setter는 운영에선 직접 지정해준다.. annotation 사용하지 않음
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    public Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
