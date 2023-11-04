package com.locationbasedfoodieservice.member.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.locationbasedfoodieservice.common.entity.Timestamped;
import com.locationbasedfoodieservice.review.entity.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@DynamicUpdate
@Entity
public class Member extends Timestamped {

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

	@Builder.Default
	@OneToMany(mappedBy = "member", orphanRemoval = true)
	private List<Review> reviewList = new ArrayList<>();

	public void update(Double latitude, Double longitude, Boolean isSuggestion) {
		if (latitude != null) {
			this.latitude = latitude;
		}
		if (longitude != null) {
			this.longitude = longitude;
		}
		if (isSuggestion != null) {
			this.isSuggestion = isSuggestion;
		}
	}
}