package com.locationbasedfoodieservice.restaurant.entity;

import jakarta.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

import com.locationbasedfoodieservice.rawrestaurant.entity.RawRestaurant;
import com.locationbasedfoodieservice.review.entity.Review;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
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

	@Column
	private Double rating;

	@OneToMany(mappedBy = "restaurant", orphanRemoval = true)
	private List<Review> reviewList = new ArrayList<>();

	@Builder
	public Restaurant(Long id, String nameAddress, String city, String name, String licenseDate,
					  String businessStatus,
					  String type, String streetAddress, String lotNumberAddress, String postalCode,
					  Double longitude, Double latitude) {
		this.id = id;
		this.nameAddress = nameAddress;
		this.city = city;
		this.name = name;
		this.licenseDate = licenseDate;
		this.businessStatus = businessStatus;
		this.type = type;
		this.streetAddress = streetAddress;
		this.lotNumberAddress = lotNumberAddress;
		this.postalCode = postalCode;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public void updateRating(double rating) {
		this.rating = rating;
	}

	public void update(RawRestaurant rawRestaurant) {
		this.city = rawRestaurant.getSigunNm();
		this.name = rawRestaurant.getBizplcNm();
		this.licenseDate = rawRestaurant.getLicensgDe();
		this.businessStatus = rawRestaurant.getBsnStateNm();
		this.type = rawRestaurant.getSanittnBizcondNm();
		this.streetAddress = rawRestaurant.getRefineRoadnmAddr();
		this.lotNumberAddress = rawRestaurant.getRefineLotnoAddr();
		this.postalCode = rawRestaurant.getRefineZipCd();
		this.longitude = rawRestaurant.getRefineWgs84Logt();
		this.latitude = rawRestaurant.getRefineWgs84Lat();
	}
}
