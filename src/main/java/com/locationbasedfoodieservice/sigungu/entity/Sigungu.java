package com.locationbasedfoodieservice.sigungu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Sigungu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String doSi;

	@Column(nullable = false)
	private String sgg;

	@Column(nullable = false)
	private Double lon;

	@Column(nullable = false)
	private Double lat;

	@Builder
	public Sigungu(String doSi, String sgg, Double lon, Double lat){
		this.doSi = doSi;
		this.sgg = sgg;
		this.lon = lon;
		this.lat = lat;
	}
}
