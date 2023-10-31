package com.locationbasedfoodieservice.member.entity;

import java.util.ArrayList;
import java.util.List;

import com.locationbasedfoodieservice.review.entity.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String account;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Double latitude;

	@Column(nullable = false)
	private Double longitude;

	@Column(nullable = false)
	private Boolean isSuggestion;

	@OneToMany(mappedBy = "member", orphanRemoval = true)
	private List<Review> reviewList = new ArrayList<>();
}
