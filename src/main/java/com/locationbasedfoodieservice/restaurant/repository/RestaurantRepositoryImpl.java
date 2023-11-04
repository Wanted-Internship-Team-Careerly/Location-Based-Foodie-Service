package com.locationbasedfoodieservice.restaurant.repository;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.locationbasedfoodieservice.restaurant.entity.QRestaurant.*;
import static com.locationbasedfoodieservice.review.entity.QReview.*;

@RequiredArgsConstructor
@Repository
public class RestaurantRepositoryImpl implements
    RestaurantRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Restaurant> findwithReviewBasedSigungu(
        String sigungu
    ) {

        List<Restaurant> temp_return = null;
        return temp_return;
    }

    ;

    public List<Restaurant> findwithReviewBasedPosition(
        double lat, double lon
    ) {
        List<Restaurant> temp_return = null;
        return temp_return;
    }

    ;
}
