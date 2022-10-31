package com.test.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
