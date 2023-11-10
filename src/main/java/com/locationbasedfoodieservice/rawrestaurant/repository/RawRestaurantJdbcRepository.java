package com.locationbasedfoodieservice.rawrestaurant.repository;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.locationbasedfoodieservice.rawrestaurant.entity.RawRestaurant;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RawRestaurantJdbcRepository {

	private final JdbcTemplate jdbcTemplate;

	@Transactional
	public void saveAll(List<RawRestaurant> rawRestaurants) {
		String sql =
			"INSERT INTO raw_restaurant (sigun_nm,sigun_cd,bizplc_nm,licensg_de,bsn_state_nm,clsbiz_de,locplc_ar,"
				+ "grad_faclt_div_nm,male_enflpsn_cnt,yy,multi_use_bizestbl_yn,grad_div_nm,tot_faclt_scale,female_enflpsn_cnt,"
				+ "bsnsite_circumfr_div_nm,sanittn_indutype_nm,sanittn_bizcond_nm,tot_emply_cnt,refine_roadnm_addr,refine_lotno_addr,"
				+ "refine_zip_cd,refine_wgs84lat,refine_wgs84logt) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		jdbcTemplate.batchUpdate(sql,
			rawRestaurants,
			rawRestaurants.size(),
			(PreparedStatement ps, RawRestaurant rawRestaurant) -> {
				ps.setString(1, rawRestaurant.getSigunNm());
				ps.setString(2, rawRestaurant.getSigunCd());
				ps.setString(3, rawRestaurant.getBizplcNm());
				ps.setString(4, rawRestaurant.getLicensgDe());
				ps.setString(5, rawRestaurant.getBsnStateNm());
				ps.setString(6, rawRestaurant.getClsbizDe());
				ps.setDouble(7, rawRestaurant.getLocplcAr());
				ps.setString(8, rawRestaurant.getGradFacltDivNm());
				ps.setInt(9, rawRestaurant.getMaleEnflpsnCnt());
				ps.setInt(10, rawRestaurant.getYy());
				ps.setString(11, rawRestaurant.getMultiUseBizestblYn());
				ps.setString(12, rawRestaurant.getGradDivNm());
				ps.setDouble(13, rawRestaurant.getTotFacltScale());
				ps.setInt(14, rawRestaurant.getFemaleEnflpsnCnt());
				ps.setString(15, rawRestaurant.getBsnsiteCircumfrDivNm());
				ps.setString(16, rawRestaurant.getSanittnIndutypeNm());
				ps.setString(17, rawRestaurant.getSanittnBizcondNm());
				ps.setInt(18, rawRestaurant.getTotEmplyCnt());
				ps.setString(19, rawRestaurant.getRefineRoadnmAddr());
				ps.setString(20, rawRestaurant.getRefineLotnoAddr());
				ps.setString(21, rawRestaurant.getRefineZipCd());
				ps.setDouble(22, rawRestaurant.getRefineWgs84Lat());
				ps.setDouble(23, rawRestaurant.getRefineWgs84Logt());
			}
		);
	}
}
