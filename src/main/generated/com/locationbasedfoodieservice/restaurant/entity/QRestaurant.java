package com.locationbasedfoodieservice.restaurant.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = -391976549L;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final StringPath businessStatus = createString("businessStatus");

    public final StringPath city = createString("city");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final StringPath licenseDate = createString("licenseDate");

    public final ComparablePath<org.locationtech.jts.geom.Point> location = createComparable("location", org.locationtech.jts.geom.Point.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath lotNumberAddress = createString("lotNumberAddress");

    public final StringPath name = createString("name");

    public final StringPath nameAddress = createString("nameAddress");

    public final StringPath postalCode = createString("postalCode");

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public final ListPath<com.locationbasedfoodieservice.review.entity.Review, com.locationbasedfoodieservice.review.entity.QReview> reviewList = this.<com.locationbasedfoodieservice.review.entity.Review, com.locationbasedfoodieservice.review.entity.QReview>createList("reviewList", com.locationbasedfoodieservice.review.entity.Review.class, com.locationbasedfoodieservice.review.entity.QReview.class, PathInits.DIRECT2);

    public final StringPath streetAddress = createString("streetAddress");

    public final StringPath type = createString("type");

    public QRestaurant(String variable) {
        super(Restaurant.class, forVariable(variable));
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurant(PathMetadata metadata) {
        super(Restaurant.class, metadata);
    }

}

