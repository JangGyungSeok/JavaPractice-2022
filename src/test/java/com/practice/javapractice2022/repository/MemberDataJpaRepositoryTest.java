package com.practice.javapractice2022.repository;

import com.practice.javapractice2022.dto.MemberDto;
import com.practice.javapractice2022.entity.Member;
import com.practice.javapractice2022.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
// @Rollback(false)
class MemberDataJpaRepositoryTest {

    @Autowired
    MemberDataJpaRepository memberDataJpaRepository;
    @Autowired
    TeamDataJpaRepository teamDataJpaRepository;

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


    // data jpa - query method 기능 검증!
    @Test
    public void findByUsernameAndAgeGreaterThenTest() {
        Member m1 = new Member("member1", 10);
        Member m2 = new Member("member1", 20);

        memberDataJpaRepository.save(m1);
        memberDataJpaRepository.save(m2);

        List<Member> result = memberDataJpaRepository.findByUsernameAndAgeGreaterThan("member1", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("member1");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    // Data JPA named query 테스트
    @Test
    public void testDataJpaNameQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberDataJpaRepository.save(m1);
        memberDataJpaRepository.save(m2);

        List<Member> result = memberDataJpaRepository.findByUsername("AAA");

        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void testDataJpaNameQuery2() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberDataJpaRepository.save(m1);
        memberDataJpaRepository.save(m2);

        List<Member> result = memberDataJpaRepository.findMember("AAA", 10);

        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void queryAnnotationTest1() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberDataJpaRepository.save(m1);
        memberDataJpaRepository.save(m2);

        List<String> usernameList = memberDataJpaRepository.findUsernameList();
//        assertThat(usernameList.get(0)).isEqualTo("AAA")
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }
    }

    // DTO 로 반환받기
    @Test
    public void queryAnnotationTest2() {
        Team team = new Team("teamA");
        teamDataJpaRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberDataJpaRepository.save(m1);

        List<MemberDto> memberDto = memberDataJpaRepository.findMemberDto();
        for(MemberDto dto : memberDto) {
            System.out.println("memberDto = " + dto);
        }
    }

    @Test
    public void findByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberDataJpaRepository.save(m1);
        memberDataJpaRepository.save(m2);

        List<Member> result = memberDataJpaRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {
            System.out.println("member = " + member);
        }
    }

    @Test
    public void returnTypeTest() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberDataJpaRepository.save(m1);
        memberDataJpaRepository.save(m2);

        System.out.println(memberDataJpaRepository.findListByUsername("AAA")); // List의 경우 null이 아닌 collection을 반환
        System.out.println(memberDataJpaRepository.findMemberByUsername("AAA")); // 단건 조회 시 null 반환
        System.out.println(memberDataJpaRepository.findOptionalByUsername("AAA"));

    }
}