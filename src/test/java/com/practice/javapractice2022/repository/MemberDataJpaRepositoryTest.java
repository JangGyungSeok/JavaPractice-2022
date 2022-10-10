package com.practice.javapractice2022.repository;

import com.practice.javapractice2022.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    // Spring Data JPA 검증
    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        Member member3 = new Member("member3");

        memberDataJpaRepository.save(member1);
        memberDataJpaRepository.save(member2);

        Member findMember1 = memberDataJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberDataJpaRepository.findById(member2.getId()).get();

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        // 변경테스트
//        findMember1.setUsername("username변경쓰!");
//        Member findMember3 = memberDataJpaRepository.findById(member1.getId()).get();
//        assertThat(findMember3.getUsername()).isEqualTo("username변경쓰!");


        List<Member> allMember = memberDataJpaRepository.findAll();
        assertThat(allMember.size()).isEqualTo(2);

        long count = memberDataJpaRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제검증
        memberDataJpaRepository.delete(member1);
        memberDataJpaRepository.delete(member2);

        long deletedCount = memberDataJpaRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }
}