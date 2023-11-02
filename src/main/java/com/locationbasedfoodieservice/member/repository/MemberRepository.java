package com.locationbasedfoodieservice.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locationbasedfoodieservice.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByAccount(String account);
}
