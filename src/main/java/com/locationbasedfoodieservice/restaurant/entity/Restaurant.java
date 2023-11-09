package com.locationbasedfoodieservice.restaurant.entity;


import java.util.ArrayList;
import java.util.List;

import com.locationbasedfoodieservice.rawrestaurant.entity.RawRestaurant;

import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.common.util.GeomUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.GeometryFactory;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(indexes = @Index(name = "location", columnList = "location"))
public class Restaurant implements Serializable {


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

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Column(columnDefinition = "POINT SRID 4326", nullable = false)
    private Point location;

    @Column
    private Double rating;

    @OneToMany(mappedBy = "restaurant", orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();


    @Builder
    public Restaurant(String nameAddress, String city, String name, String licenseDate,
        String businessStatus,
        String type, String streetAddress, String lotNumberAddress, String postalCode,
        Double longitude, Double latitude, Double rating) {

        GeomUtil geomutil = new GeomUtil();
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
        this.rating = rating;
        this.location = geomutil.createPoint(longitude, latitude);
    }

    public void updateRating(double rating) {
        this.rating = rating;
    }



	public void update(RawRestaurant rawRestaurant) {
        GeomUtil geomutil = new GeomUtil();

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
        this.location = geomutil.createPoint(longitude, latitude);

    }
}
