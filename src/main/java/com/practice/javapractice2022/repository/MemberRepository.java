package com.practice.javapractice2022.repository;

import com.practice.javapractice2022.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// data jpa repository이다. JpaRepository<관련entity, ID타입>
public interface MemberRepository extends JpaRepository<Member, Long> {

}
