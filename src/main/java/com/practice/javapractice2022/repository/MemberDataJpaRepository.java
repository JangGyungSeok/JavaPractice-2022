package com.practice.javapractice2022.repository;

import com.practice.javapractice2022.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// data jpa repository이다. JpaRepository<관련entity, ID타입>
public interface MemberDataJpaRepository extends JpaRepository<Member, Long> {

    // querymethod 기능 (메서드명으로 sql문을 생성한다)
    List<Member> findByUsername(String username);

    // 운영에서 query method는 메서드명이 너무 길어질 우려가 있다. (김영한 선생님은 2개까진 한다고함)
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

}
