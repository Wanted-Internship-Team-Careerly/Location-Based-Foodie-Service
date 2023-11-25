package com.locationbasedfoodieservice.review.entity;

import com.locationbasedfoodieservice.common.entity.Timestamped;
import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer score;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@Builder
	public Review(Long id, Integer score, String content, Member member, Restaurant restaurant) {
		this.id = id;
		this.score = score;
		this.content = content;
		this.member = member;
		this.restaurant = restaurant;
	}

	public void addRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
		restaurant.getReviewList().add(this);
	}
}
