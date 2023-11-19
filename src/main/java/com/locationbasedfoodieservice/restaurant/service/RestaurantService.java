package com.locationbasedfoodieservice.restaurant.service;

import static com.locationbasedfoodieservice.common.error.CustomErrorCode.INVALID_SIGUNGU_EXCEPTION;
import static com.locationbasedfoodieservice.common.error.CustomErrorCode.SIGUNGU_NOT_FOUND;

import com.locationbasedfoodieservice.common.exception.CustomException;
import com.locationbasedfoodieservice.restaurant.dto.RestaurantDetailResponseDto;
import com.locationbasedfoodieservice.restaurant.dto.RestaurantsResponseDto;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import com.locationbasedfoodieservice.sigungu.entity.Sigungu;
import com.locationbasedfoodieservice.sigungu.repository.SigunguRepository;
import com.locationbasedfoodieservice.common.util.DistanceUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Restaurant API", description = "Restaurant 관련 API 정보를 담고 있습니다.")
@Slf4j
@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final SigunguRepository sigunguRepository;
    private DistanceUtil distanceUtil = new DistanceUtil();
    @Transactional(readOnly = true)
    public RestaurantsResponseDto getRestaurantsBySigungu(String reqSigungu, String sort, double range) {

        //Sigungu는 "광역권,도시이름"으로 받는다고 가정 (ex : "경기,군포시")
        Sigungu sigungu = sigunguValidator(reqSigungu);
        String sigunguSearchPattern = searchPatternGen(reqSigungu);
        boolean sortbyRating = !sort.equals("거리순");

        //sigungu와 일치하는 음식점 불러오기
        List<Restaurant> resturants = restaurantRepository.findBySigungu(sigunguSearchPattern,sortbyRating);
        //평점순 (이미 되어있음) , 거리순 sorting
        //TODO: spatial idnex 기반 거리 정렬
        List<RestaurantDetailResponseDto> sortedRestaurants = distanceUtil.restaurantListSortByDistance(resturants, sigungu, range, sortbyRating);

        return RestaurantsResponseDto.from(sortedRestaurants);
    }
    private Sigungu sigunguValidator(String reqSigungu){
        String[] splitSgg = reqSigungu.split(",",-1);
        String Dosi = splitSgg[0];
        if (splitSgg.length < 2) {
            throw new CustomException(INVALID_SIGUNGU_EXCEPTION);
        }
        String Sgg = splitSgg[1];
        Sigungu sigungu = sigunguRepository.findByDoSiAndSgg(Dosi, Sgg).orElseThrow(
            () -> new CustomException(SIGUNGU_NOT_FOUND)
        );
        return sigungu;
    }
    private String searchPatternGen(String reqSigungu){
        String[] splitSgg = reqSigungu.split(",",-1);
        String Dosi = splitSgg[0];
        String Sgg = splitSgg[1];
        return(Dosi + "%" + Sgg + "%");

    }
}
