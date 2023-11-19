/**
 * 경기도 일반음식점(김밥(도시락))의 데이터를 가져와
 * ...이 키로 RawRestaurant를 찾아서 dirty checking 후
 * RawRestaurant를 update합니다.
 */
package com.locationbasedfoodieservice.rawrestaurant.scheduler;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.locationbasedfoodieservice.rawrestaurant.entity.RawRestaurant;
import com.locationbasedfoodieservice.rawrestaurant.repository.RawRestaurantJdbcRepository;
import com.locationbasedfoodieservice.rawrestaurant.repository.RawRestaurantRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Open API Scheduler Log")
@Component
@RequiredArgsConstructor
@Transactional
public class RawRestaurantScheduler {

	private final RestTemplate restTemplate;
	private final RawRestaurantRepository rawRestaurantRepository;
	private final RawRestaurantJdbcRepository rawRestaurantJdbcRepository;

	@Value("${api.key}")
	private String apiKey;
	private Long dataCount;
	private final Long BATCH_SIZE = 999L;    // 한 번의 API 요청에 999개까지의 데이터만을 받아올 수 있습니다.

	/**
	 * 크론 스케줄링
	 * 첫 번째 필드: 초 (0-59)
	 * 두 번째 필드: 분 (0-59)
	 * 세 번째 필드: 시간 (0-23)
	 * 네 번째 필드: 일 (1-31)
	 * 다섯 번째 필드: 월 (1-12)
	 * 여섯 번째 필드: 요일 (0-6, 일요일부터 토요일까지, 일요일=0 또는 7)
	 */

	// 데이터 총 개수를 가져와서 dataCount에 넣어줍니다.
	@Scheduled(cron = "0 0 4 * * 6")
	public void updateRawRestaurant() {
		rawRestaurantRepository.deleteAll();

		for (DataType type : DataType.values()) {
			countData(type.getUrl());
			updateData(type.getUrl());
		}
	}

	/**
	 * 데이터 총 개수를 가져옵니다.
	 *
	 * @param type 업장종류(김밥, 카페, 일식, ...)
	 */
	private void countData(String type) {
		URI uri = UriComponentsBuilder
			.fromUriString("https://openapi.gg.go.kr/" + type)
			.queryParam("KEY", apiKey)
			.queryParam("Type", "json")
			.queryParam("pSize", 1)
			.encode(StandardCharsets.UTF_8)
			.build()
			.toUri();

		RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

		dataCount = new JSONObject(responseEntity.getBody())
			.getJSONArray(type).getJSONObject(0)
			.getJSONArray("head").getJSONObject(0)
			.getLong("list_total_count");

		log.info("dataCount = " + dataCount);
	}

	/**
	 * 데이터 총 개수에 맞춰 for문을 돌면서 데이터를 가져옵니다.
	 *
	 * @param type 업장종류(김밥, 카페, 일식, ...)
	 */
	private void updateData(String type) {
		long page = (dataCount / BATCH_SIZE) + 1;

		log.info(type + " Update Scheduling Start");

		for (int i = 1; i <= page; i++) {
			URI uri = UriComponentsBuilder
				.fromUriString("https://openapi.gg.go.kr/" + type)
				.queryParam("KEY", apiKey)
				.queryParam("Type", "json")
				.queryParam("pIndex", i)
				.queryParam("pSize", BATCH_SIZE)
				.encode(StandardCharsets.UTF_8)
				.build()
				.toUri();

			RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
			ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

			JSONArray rawRestaurants = new JSONObject(responseEntity.getBody())
				.getJSONArray(type).getJSONObject(1)
				.getJSONArray("row");

			saveRawRestaurantsFromJson(rawRestaurants);
		}
		log.info(type + " Update Scheduling End");
	}

	/**
	 * JSON 배열 raw데이터들을 받아서 저장하는 메서드입니다.
	 *
	 * @param rawRestaurants
	 */
	private void saveRawRestaurantsFromJson(JSONArray rawRestaurants) {
		// DB에 존재하지 않을 경우 list에 담아두고 한꺼번에 저장하기 위한 용도입니다.
		List<RawRestaurant> saveRawDataList = new ArrayList<>();

		for (int j = 0; j < rawRestaurants.length(); j++) {
			JSONObject rawRestaurant = rawRestaurants.getJSONObject(j);

			RawRestaurant newRawRestaurant = from(rawRestaurant);
			saveRawDataList.add(newRawRestaurant);
		}
		rawRestaurantJdbcRepository.saveAll(saveRawDataList);
	}

	/**
	 * JSON을 객체화해주는 메서드입니다.
	 *
	 * @param rawRestaurant JSON
	 * @return RawRestaurant 객체
	 */
	public RawRestaurant from(JSONObject rawRestaurant) {
		return RawRestaurant.builder()
			.sigunNm(rawRestaurant.optString("SIGUN_NM"))
			.sigunCd(rawRestaurant.optString("SIGUN_CD"))
			.bizplcNm(rawRestaurant.optString("BIZPLC_NM"))
			.licensgDe(rawRestaurant.optString("LICENSG_DE"))
			.bsnStateNm(rawRestaurant.optString("BSN_STATE_NM"))
			.clsbizDe(rawRestaurant.optString("CLSBIZ_DE"))
			.locplcAr(Double.parseDouble(rawRestaurant.optString("LOCPLC_AR", "0")))
			.gradFacltDivNm(rawRestaurant.optString("GRAD_FACLT_DIV_NM"))
			.maleEnflpsnCnt(rawRestaurant.optInt("MALE_ENFLP_SN_CNT"))
			.yy(Integer.parseInt(rawRestaurant.optString("YY", "0")))
			.multiUseBizestblYn(rawRestaurant.optString("MULTI_USE_BIZESTBL_YN"))
			.gradDivNm(rawRestaurant.optString("GRAD_DIV_NM"))
			.totFacltScale(Double.parseDouble(rawRestaurant.optString("TOT_FACLT_SCALE", "0")))
			.femaleEnflpsnCnt(rawRestaurant.optInt("FEMALE_ENFLPSN_CNT"))
			.bsnsiteCircumfrDivNm(rawRestaurant.optString("BSNSITE_CIRCUMFR_DIV_NM"))
			.sanittnIndutypeNm(rawRestaurant.optString("SANITTN_INDUTYPE_NM"))
			.sanittnBizcondNm(rawRestaurant.optString("SANITTN_BIZCOND_NM"))
			.totEmplyCnt(rawRestaurant.optInt("TOT_EMPLY_CNT"))
			.refineRoadnmAddr(rawRestaurant.optString("REFINE_ROADNM_ADDR"))
			.refineLotnoAddr(rawRestaurant.optString("REFINE_LOTNO_ADDR"))
			.refineZipCd(rawRestaurant.optString("REFINE_ZIP_CD"))
			.refineWgs84Lat(Double.parseDouble(rawRestaurant.optString("REFINE_WGS84_LAT", "0")))
			.refineWgs84Logt(Double.parseDouble(rawRestaurant.optString("REFINE_WGS84_LOGT", "0")))
			.build();
	}

}
