package com.locationbasedfoodieservice.restaurant.entity;

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
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nameAddress;

	@Column
	private String city;

	@Column
	private String name;

	@Column
	private String licenseDate;

	@Column
	private String businessStatus;

	@Column
	private String type;

	@Column
	private String streetAddress;

	@Column
	private String lotNumberAddress;

	@Column
	private String postalCode;

	@Column
	private Double longitude;

	@Column
	private Double latitude;

	@OneToMany(mappedBy = "restaurant", orphanRemoval = true)
	private List<Review> reviewList = new ArrayList<>();
}
