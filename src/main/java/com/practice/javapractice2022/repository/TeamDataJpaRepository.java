package com.practice.javapractice2022.repository;


import com.practice.javapractice2022.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDataJpaRepository extends JpaRepository<Team, Long> {
}
