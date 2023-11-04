package com.locationbasedfoodieservice.review.repository;

import static com.locationbasedfoodieservice.review.entity.QReview.review;

import com.locationbasedfoodieservice.review.entity.Review;
import com.querydsl.jpa.impl.JPAQuery;
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

        JPAQuery<Review> query = queryFactory.select(review)
                .where(review.restaurant.id.eq(restaurantId))
                .fetchAll();

        return query.fetch().size();

    }

}
