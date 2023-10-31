package com.locationbasedfoodieservice.rawstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RawStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String sigunNm;

	@Column
	private Integer sigunCd;

	@Column
	private String bizplcNm;

	@Column
	private Integer licensgDe;

	@Column
	private String bsnStateNm;

	@Column
	private Integer clsbizDe;

	@Column
	private Double locplcAr;

	@Column
	private String gradFacltDivNm;

	@Column
	private Integer maleEnflpsnCnt;

	@Column
	private Integer yy;

	@Column
	private String multiUseBizestblYn;

	@Column
	private String gradDivNm;

	@Column
	private Double totFacltScale;

	@Column
	private Integer femaleEnflpsnCnt;

	@Column
	private String bsnsiteCircumfrDivNm;

	@Column
	private String sanittnIndutypeNm;

	@Column
	private String sanittnBizcondNm;

	@Column
	private Integer totEmptyCnt;

	@Column
	private String refineRoadnmAddr;

	@Column
	private String refineLotnoAddr;

	@Column
	private Integer refineZipCd;

	@Column
	private Double refineWgs84Lat;

	@Column
	private Double refineWgs84Logt;
}
