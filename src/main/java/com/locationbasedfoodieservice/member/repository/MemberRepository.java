package com.locationbasedfoodieservice.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locationbasedfoodieservice.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findByAccount(String account);

	boolean existsByAccount(String account);
}
