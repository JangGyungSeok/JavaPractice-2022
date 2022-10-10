package com.practice.javapractice2022.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter // setter는 운영에선 직접 지정해준다.. annotation 사용하지 않음
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"}) // 연관 테이블은 ToString하지 않는것이 좋다.
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;


    // 연관 테이블 (entity) 매핑
    @ManyToOne(fetch = FetchType.LAZY) // eager로 세팅하면 성능이 좋지 않다. 무조건 LAZY!!
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    // team 변경 시 연관테이블도 설정해줘야함
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
