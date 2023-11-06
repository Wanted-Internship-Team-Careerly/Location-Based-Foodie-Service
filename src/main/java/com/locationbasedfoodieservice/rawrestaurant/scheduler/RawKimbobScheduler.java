/**
 * 경기도 일반음식점(김밥(도시락))의 데이터를 가져와
 * ...이 키로 RawRestaurant를 찾아서 dirty checking 후
 * RawRestaurant를 update합니다.
 */
package com.locationbasedfoodieservice.rawrestaurant.scheduler;

import com.locationbasedfoodieservice.rawrestaurant.entity.RawRestaurant;
import com.locationbasedfoodieservice.rawrestaurant.repository.RawRestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Slf4j(topic = "Open Api Scheduler Log")
@Component
@RequiredArgsConstructor
public class RawKimbobScheduler {
	private final RestTemplate restTemplate;
	private final RawRestaurantRepository rawRestaurantRepository;

	@Value("${api.key}")
	private String API_KEY;

	private Long dataCount;

	// 크론 스케줄링
	// 첫 번째 필드: 초 (0-59)
	// 두 번째 필드: 분 (0-59)
	// 세 번째 필드: 시간 (0-23)
	// 네 번째 필드: 일 (1-31)
	// 다섯 번째 필드: 월 (1-12)
	// 여섯 번째 필드: 요일 (0-6, 일요일부터 토요일까지, 일요일=0 또는 7)

	// 데이터 총 개수를 가져와서 dataCount에 넣어줍니다.
	@Scheduled(cron = "0 0 4 * * 6")
	public void countingData() {
		URI uri = UriComponentsBuilder
				.fromUriString("https://openapi.gg.go.kr/Genrestrtlunch")
				.queryParam("KEY", API_KEY)
				.queryParam("Type", "json")
				.queryParam("pSize", 1)
				.encode(StandardCharsets.UTF_8)
				.build()
				.toUri();

		RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
		ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

		dataCount = new JSONObject(responseEntity.getBody())
				.getJSONArray("Genrestrtlunch").getJSONObject(0)
				.getJSONArray("head").getJSONObject(0)
				.getLong("list_total_count");

		log.info("dataCount = " + dataCount);
	}

	// 데이터 총 갯수에 맞춰 for문을 돌면서 데이터를 가져옵니다.
	// (데이터를 한 번에 1000개 이상으로 가져오지 못하기 때문)
	@Scheduled(cron = "30 0 4 * * 6")    // 토요일 새벽 4시 업데이트
	public void updateKimbob() {
		long number = dataCount / 999 + 1;

		for (int i = 1; i <= number; i++) {
			URI uri = UriComponentsBuilder
					.fromUriString("https://openapi.gg.go.kr/Genrestrtlunch")
					.queryParam("KEY", API_KEY)
					.queryParam("Type", "json")
					.queryParam("pIndex", i)
					.queryParam("pSize", 999)
					.encode(StandardCharsets.UTF_8)
					.build()
					.toUri();

			RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
			ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

			JSONArray rawRestaurants = new JSONObject(responseEntity.getBody())
					.getJSONArray("Genrestrtlunch").getJSONObject(1)
					.getJSONArray("row");

			for (int j = 0; j < rawRestaurants.length(); j++) {
				JSONObject rawRestaurant = rawRestaurants.getJSONObject(j);

				log.info("지금은 " + j + "번째 rawRestaurant입니다\n");
				log.info("rawRestaurant: \n" + rawRestaurant + "\n");

				// 사업장명과 우편번호가 없으면 저장하거나 업데이트하지 않습니다.
				if (rawRestaurant.isNull("BIZPLC_NM") || rawRestaurant.isNull("REFINE_ZIP_CD")) {
					continue;
				}

				String BIZPLC_NM = rawRestaurant.getString("BIZPLC_NM");
				String REFINE_ZIP_CD = rawRestaurant.getString("REFINE_ZIP_CD");

				RawRestaurant targetRawRestaurant = rawRestaurantRepository
						.findByBizplcNmAndRefineZipCd(BIZPLC_NM, Integer.parseInt(REFINE_ZIP_CD))
						.orElse(null);

				if (targetRawRestaurant == null) {
					RawRestaurant newRawRestaurant = RawRestaurant.builder()
							.sigunNm(rawRestaurant.isNull("SIGUN_NM") ? "" : rawRestaurant.getString("SIGUN_NM"))
							.sigunCd(rawRestaurant.isNull("SIGUN_CD") ? "" : rawRestaurant.getString("SIGUN_CD"))
							.bizplcNm(rawRestaurant.getString("BIZPLC_NM"))
							.licensgDe(rawRestaurant.isNull("LICENSG_DE") ? "" : rawRestaurant.getString("LICENSG_DE"))
							.bsnStateNm(rawRestaurant.isNull("BSN_STATE_NM") ? "" : rawRestaurant.getString("BSN_STATE_NM"))
							.clsbizDe(rawRestaurant.isNull("CLSBIZ_DE") ? "" : rawRestaurant.getString("CLSBIZ_DE"))
							.locplcAr(rawRestaurant.isNull("LOCPLC_AR") ? 0 : Double.parseDouble(rawRestaurant.getString("LOCPLC_AR")))
							.gradFacltDivNm(rawRestaurant.isNull("GRAD_FACLT_DIV_NM") ? "" : rawRestaurant.getString("GRAD_FACLT_DIV_NM"))
							.maleEnflpsnCnt(rawRestaurant.isNull("MALE_ENFLP_SN_CNT") ? 0 : rawRestaurant.getInt("MALE_ENFLP_SN_CNT"))
							.yy(rawRestaurant.isNull("YY") ? 0 : Integer.parseInt(rawRestaurant.getString("YY")))
							.multiUseBizestblYn(rawRestaurant.isNull("MULTI_USE_BIZESTBL_YN") ? "" : rawRestaurant.getString("MULTI_USE_BIZESTBL_YN"))
							.gradDivNm(rawRestaurant.isNull("GRAD_DIV_NM") ? "" : rawRestaurant.getString("GRAD_DIV_NM"))
							.totFacltScale(rawRestaurant.isNull("TOT_FACLT_SCALE") ? 0 : Double.parseDouble(rawRestaurant.getString("TOT_FACLT_SCALE")))
							.femaleEnflpsnCnt(rawRestaurant.isNull("FEMALE_ENFLPSN_CNT") ? 0 : rawRestaurant.getInt("FEMALE_ENFLPSN_CNT"))
							.bsnsiteCircumfrDivNm(rawRestaurant.isNull("BENSITE_CIRCUMFR_DIV_NM") ? "" : rawRestaurant.getString("BSNSITE_CIRCUMFR_DIV_NM"))
							.sanittnIndutypeNm(rawRestaurant.isNull("SANITTN_INDUTYPE_NM") ? "" : rawRestaurant.getString("SANITTN_INDUTYPE_NM"))
							.sanittnBizcondNm(rawRestaurant.isNull("SANITTN_BIZCOND_NM") ? "" : rawRestaurant.getString("SANITTN_BIZCOND_NM"))
							.totEmplyCnt(rawRestaurant.isNull("TOT_EMPLY_CNT") ? 0 : rawRestaurant.getInt("TOT_EMPLY_CNT"))
							.refineRoadnmAddr(rawRestaurant.isNull("REFINE_ROADNM_ADDR") ? "" : rawRestaurant.getString("REFINE_ROADNM_ADDR"))
							.refineLotnoAddr(rawRestaurant.isNull("REFINE_LOTNO_ADDR") ? "" : rawRestaurant.getString("REFINE_LOTNO_ADDR"))
							.refineZipCd(Integer.parseInt(rawRestaurant.getString("REFINE_ZIP_CD")))
							.refineWgs84Lat(rawRestaurant.isNull("REFINE_WGS84_LAT") ? 0 : Double.parseDouble(rawRestaurant.getString("REFINE_WGS84_LAT")))
							.refineWgs84Logt(rawRestaurant.isNull("REFINE_WGS84_LOGT") ? 0 : Double.parseDouble(rawRestaurant.getString("REFINE_WGS84_LOGT")))
							.build();
					rawRestaurantRepository.save(newRawRestaurant);

					log.info("위치 : " + rawRestaurant.getString("SIGUN_NM"));
					log.info("이름 : " + rawRestaurant.getString("BIZPLC_NM"));
					continue;
				}

				targetRawRestaurant.update(rawRestaurant);
			}

		}
	}

}
