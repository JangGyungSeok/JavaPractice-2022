package com.practice.javapractice2022.repository;

import com.practice.javapractice2022.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// data jpa repository이다. JpaRepository<관련entity, ID타입>
public interface MemberDataJpaRepository extends JpaRepository<Member, Long> {

    // querymethod 기능 (메서드명으로 sql문을 생성한다)
//    List<Member> findByUsername(String username);

    // 운영에서 query method는 메서드명이 너무 길어질 우려가 있다. (김영한 선생님은 2개까진 한다고함)
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //Param annotation은 ibatis꺼 쓰지 말것!
    // Query annotation이 있으면 namedQuery 를 찾아 매핑 근데 주석해도 잘됨
    // Entity에 named query가 있고 메서드가 named query와 같다면 named query가 우선된다.
    // named query는 실무에서 사용안한다.
    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    
    // 실무에 많이 활용하는 형태
    // 문자열이지만 application loading 시점에 parsing 해보고 에러발생시킴 (이름을 지정하지 않은 named query라고 생각해면 된다.)
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findMember(@Param("username") String username, @Param("age") int age);
}
