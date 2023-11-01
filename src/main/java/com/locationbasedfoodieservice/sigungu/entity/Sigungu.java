package com.locationbasedfoodieservice.sigungu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sigungu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String doSi;

	@Column(nullable = false)
	private String ssg;

	@Column(nullable = false)
	private Double lon;

	@Column(nullable = false)
	private Double lat;
}
