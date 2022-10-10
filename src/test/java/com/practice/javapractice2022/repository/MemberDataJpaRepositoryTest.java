package com.practice.javapractice2022.repository;

import com.practice.javapractice2022.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
// @Rollback(false)
class MemberDataJpaRepositoryTest {

    @Autowired
    MemberDataJpaRepository memberDataJpaRepository;

    @Test
    public void testMember() {
        Member member = new Member("member1");
        Member savedMember = memberDataJpaRepository.save(member);

        Member findMember = memberDataJpaRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);

    }

    // class com.sun.proxy.$Proxy123 라고 나옴
    @Test
    public void testDataJpa() {
        System.out.println("정보 확인하기 !! :" + memberDataJpaRepository.getClass());
    }
}