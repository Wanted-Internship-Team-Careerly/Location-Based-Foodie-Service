package com.locationbasedfoodieservice.review.repository;

import static com.locationbasedfoodieservice.review.entity.QReview.review;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    // restaurantId 기준 리뷰 개수를 찾는다
    @Override
    public Integer findCountByRestaurantId(Long restaurantId) {

        return Math.toIntExact(queryFactory
                .select(review.count())
                .where(review.restaurant.id.eq(restaurantId))
                .from(review)
                .fetchFirst());
    }

}
