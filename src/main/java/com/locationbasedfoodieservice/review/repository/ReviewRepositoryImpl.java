package com.locationbasedfoodieservice.review.repository;

import static com.locationbasedfoodieservice.review.entity.QReview.review;

import com.locationbasedfoodieservice.review.entity.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
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

    @Override
    public List<Review> findReviewsByRestaurantId(Long restaurantId) {

        return queryFactory
                .select(review)
                .where(review.restaurant.id.eq(restaurantId))
                .from(review)
                .fetch();
    }

}
