package com.locationbasedfoodieservice.common.util;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.sigungu.entity.Sigungu;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DistanceUtil {

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 지구의 반경 (km 단위)
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // 거리 계산

        return distance;
    }

    public List<Restaurant> restaurantListSortByDistance(List<Restaurant> restaurantList, Sigungu sigungu,
        int range, boolean sortByRating) {
        Stream<SimpleEntry<Restaurant, Double>> restaurants = restaurantList.stream()
            // 거리 계산
            .map(r -> new SimpleEntry<>(r,
                calculateDistance(sigungu.getLat(), sigungu.getLon(), r.getLatitude(),
                    r.getLongitude())))
            // calculateDistance 결과가 range 초과인 restaurant 제거
            .filter(entry ->entry.getValue() <= range);

        Stream<Restaurant> sortedRestaurant = sortByRating
            ? restaurants.map(Entry::getKey)
        :restaurants
            // 거리에 따라 정렬
            .sorted(Comparator.comparingDouble(Entry::getValue))
            .map(Entry::getKey);

        return sortedRestaurant.collect(Collectors.toList());
    }
}
