package com.locationbasedfoodieservice.rawrestaurant.entity;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.json.JSONObject;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Table(uniqueConstraints =
		{@UniqueConstraint(columnNames = {"bizplcNm", "refineZipCd"})})
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

	public void update(JSONObject rawRestaurant) {
		this.sigunNm = rawRestaurant.optString("SIGUN_NM");
		this.sigunCd = rawRestaurant.optString("SIGUN_CD");
		this.bizplcNm = rawRestaurant.optString("BIZPLC_NM");
		this.licensgDe = rawRestaurant.optString("LICENSG_DE");
		this.bsnStateNm = rawRestaurant.optString("BSN_STATE_NM");
		this.clsbizDe = rawRestaurant.optString("CLSBIZ_DE");
		this.locplcAr = Double.parseDouble(rawRestaurant.optString("LOCPLC_AR", "0"));
		this.gradFacltDivNm = rawRestaurant.optString("GRAD_FACLT_DIV_NM");
		this.maleEnflpsnCnt = rawRestaurant.optInt("MALE_ENFLP_SN_CNT");
		this.yy = Integer.parseInt(rawRestaurant.optString("YY", "0"));
		this.multiUseBizestblYn = rawRestaurant.optString("MULTI_USE_BIZESTBL_YN");
		this.gradDivNm = rawRestaurant.optString("GRAD_DIV_NM");
		this.totFacltScale = Double.parseDouble(rawRestaurant.optString("TOT_FACLT_SCALE", "0"));
		this.femaleEnflpsnCnt = rawRestaurant.optInt("FEMALE_ENFLPSN_CNT");
		this.bsnsiteCircumfrDivNm = rawRestaurant.optString("BSNSITE_CIRCUMFR_DIV_NM");
		this.sanittnIndutypeNm = rawRestaurant.optString("SANITTN_INDUTYPE_NM");
		this.sanittnBizcondNm = rawRestaurant.optString("SANITTN_BIZCOND_NM");
		this.totEmplyCnt = rawRestaurant.optInt("TOT_EMPLY_CNT");
		this.refineRoadnmAddr = rawRestaurant.optString("REFINE_ROADNM_ADDR");
		this.refineLotnoAddr = rawRestaurant.optString("REFINE_LOTNO_ADDR");
		this.refineZipCd = rawRestaurant.optString("REFINE_ZIP_CD");
		this.refineWgs84Lat = Double.parseDouble(rawRestaurant.optString("REFINE_WGS84_LAT", "0"));
		this.refineWgs84Logt = Double.parseDouble(rawRestaurant.optString("REFINE_WGS84_LOGT", "0"));
	}

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
