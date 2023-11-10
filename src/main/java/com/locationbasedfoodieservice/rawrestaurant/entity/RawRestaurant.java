package com.locationbasedfoodieservice.rawrestaurant.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class RawRestaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	// 시군명
	private String sigunNm;
	// 시군코드
	private String sigunCd;
	// 사업장명
	private String bizplcNm;
	// 인허가일자
	private String licensgDe;
	// 영업상태명
	private String bsnStateNm;
	// 폐업일자
	private String clsbizDe;
	// 소재지면적(m2)
	private Double locplcAr;
	// 급수시설구분명
	private String gradFacltDivNm;
	// 남성종사자수(명)
	private Integer maleEnflpsnCnt;
	// 년도
	private Integer yy;
	// 다중이용업소여부
	private String multiUseBizestblYn;
	// 등급구분명
	private String gradDivNm;
	// 총시설규모(m2)
	private Double totFacltScale;
	// 여성종사자수(명)
	private Integer femaleEnflpsnCnt;
	// 영업장주변구분명
	private String bsnsiteCircumfrDivNm;
	// 위생업종명
	private String sanittnIndutypeNm;
	// 위생업태명
	private String sanittnBizcondNm;
	// 총종업원수
	private Integer totEmplyCnt;
	// 소재지도로명주소
	private String refineRoadnmAddr;
	// 소재지지번주소
	private String refineLotnoAddr;
	// 소재지우편번호
	private String refineZipCd;
	// WGS84위도
	private Double refineWgs84Lat;
	// WGS84경도
	private Double refineWgs84Logt;

	public Restaurant toRestaurant(String uniqueKey) {
		return Restaurant.builder()
			.nameAddress(uniqueKey)
			.city(this.sigunNm)
			.name(this.bizplcNm)
			.licenseDate(this.licensgDe)
			.businessStatus(this.bsnStateNm)
			.type(this.sanittnBizcondNm)
			.streetAddress(this.refineRoadnmAddr)
			.lotNumberAddress(this.refineLotnoAddr)
			.postalCode(this.refineZipCd)
			.longitude(this.refineWgs84Logt)
			.latitude(this.refineWgs84Lat)
			.build();
	}
}
