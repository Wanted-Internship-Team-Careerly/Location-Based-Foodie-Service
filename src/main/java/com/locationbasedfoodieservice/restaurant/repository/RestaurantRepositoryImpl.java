package com.locationbasedfoodieservice.restaurant.repository;

import com.locationbasedfoodieservice.restaurant.entity.QRestaurant;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.querydsl.core.types.Order;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.locationbasedfoodieservice.restaurant.entity.QRestaurant.*;
import static com.locationbasedfoodieservice.review.entity.QReview.*;

@RequiredArgsConstructor
@Repository
public class RestaurantRepositoryImpl implements
    RestaurantRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Restaurant> findBySigungu(
        String sigunguSearchPattern, boolean orderByRating
    ) {
        QRestaurant qRestaurant = QRestaurant.restaurant;

        JPAQuery<Restaurant> query = jpaQueryFactory.selectFrom(qRestaurant)
            .where(qRestaurant.streetAddress.like(sigunguSearchPattern));

        OrderSpecifier<Double> orderSpecifier = new OrderSpecifier<>(Order.ASC, qRestaurant.rating);
        if (orderByRating) {
            query.orderBy(orderSpecifier);
        }

        List<Restaurant> restaurantList = query.fetch();
        return restaurantList;
    }


    public List<Restaurant> findByPosition(
        double lat, double lon, boolean orderByRating
    ) {
        List<Restaurant> temp_return = null;
        return temp_return;
    }

}
