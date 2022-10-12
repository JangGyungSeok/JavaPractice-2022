package com.practice.javapractice2022.dto;

import lombok.Data;

@Data // getter, setter 다있어서 왠만하면 entity에서는 사용하지 않는다.
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
}
