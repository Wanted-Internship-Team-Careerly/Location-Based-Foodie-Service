package com.locationbasedfoodieservice.rawrestaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.json.JSONObject;

@Entity
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
	private Integer refineZipCd;
	// WGS84위도
	private Double refineWgs84Lat;
	// WGS84경도
	private Double refineWgs84Logt;

	public void update(JSONObject rawRestaurant) {
		this.sigunNm = rawRestaurant.isNull("SIGUN_NM") ? "" : rawRestaurant.getString("SIGUN_NM");
		this.sigunCd = rawRestaurant.isNull("SIGUN_CD") ? "" : rawRestaurant.getString("SIGUN_CD");
		this.licensgDe = rawRestaurant.isNull("LICENSG_DE") ? "" : rawRestaurant.getString("LICENSG_DE");
		this.bsnStateNm = rawRestaurant.isNull("BSN_STATE_NM") ? "" : rawRestaurant.getString("BSN_STATE_NM");
		this.clsbizDe = rawRestaurant.isNull("CLSBIZ_DE") ? "" : rawRestaurant.getString("CLSBIZ_DE");
		this.locplcAr = rawRestaurant.isNull("LOCPLC_AR") ? 0 : Double.parseDouble(rawRestaurant.getString("LOCPLC_AR"));
		this.gradFacltDivNm = rawRestaurant.isNull("GRAD_FACLT_DIV_NM") ? "" : rawRestaurant.getString("GRAD_FACLT_DIV_NM");
		this.maleEnflpsnCnt = rawRestaurant.isNull("MALE_ENFLP_SN_CNT") ? 0 : rawRestaurant.getInt("MALE_ENFLP_SN_CNT");
		this.yy = rawRestaurant.isNull("YY") ? 0 : Integer.parseInt(rawRestaurant.getString("YY"));
		this.multiUseBizestblYn = rawRestaurant.isNull("MULTI_USE_BIZESTBL_YN") ? "" : rawRestaurant.getString("MULTI_USE_BIZESTBL_YN");
		this.gradDivNm = rawRestaurant.isNull("GRAD_DIV_NM") ? "" : rawRestaurant.getString("GRAD_DIV_NM");
		this.totFacltScale = rawRestaurant.isNull("TOT_FACLT_SCALE") ? 0 : Double.parseDouble(rawRestaurant.getString("TOT_FACLT_SCALE"));
		this.femaleEnflpsnCnt = rawRestaurant.isNull("FEMALE_ENFLPSN_CNT") ? 0 : rawRestaurant.getInt("FEMALE_ENFLPSN_CNT");
		this.bsnsiteCircumfrDivNm = rawRestaurant.isNull("BENSITE_CIRCUMFR_DIV_NM") ? "" : rawRestaurant.getString("BSNSITE_CIRCUMFR_DIV_NM");
		this.sanittnIndutypeNm = rawRestaurant.isNull("SANITTN_INDUTYPE_NM") ? "" : rawRestaurant.getString("SANITTN_INDUTYPE_NM");
		this.sanittnBizcondNm = rawRestaurant.isNull("SANITTN_BIZCOND_NM") ? "" : rawRestaurant.getString("SANITTN_BIZCOND_NM");
		this.totEmplyCnt = rawRestaurant.isNull("TOT_EMPLY_CNT") ? 0 : rawRestaurant.getInt("TOT_EMPLY_CNT");
		this.refineRoadnmAddr = rawRestaurant.isNull("REFINE_ROADNM_ADDR") ? "" : rawRestaurant.getString("REFINE_ROADNM_ADDR");
		this.refineLotnoAddr = rawRestaurant.isNull("REFINE_LOTNO_ADDR") ? "" : rawRestaurant.getString("REFINE_LOTNO_ADDR");
		this.refineWgs84Lat = rawRestaurant.isNull("REFINE_WGS84_LAT") ? 0 : Double.parseDouble(rawRestaurant.getString("REFINE_WGS84_LAT"));
		this.refineWgs84Logt = rawRestaurant.isNull("REFINE_WGS84_LOGT") ? 0 : Double.parseDouble(rawRestaurant.getString("REFINE_WGS84_LOGT"));
	}
}
