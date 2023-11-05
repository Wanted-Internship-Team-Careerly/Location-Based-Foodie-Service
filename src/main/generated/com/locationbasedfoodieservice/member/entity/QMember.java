package com.locationbasedfoodieservice.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1222761835L;

    public static final QMember member = new QMember("member1");

    public final com.locationbasedfoodieservice.common.entity.QTimestamped _super = new com.locationbasedfoodieservice.common.entity.QTimestamped(this);

    public final StringPath account = createString("account");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isSuggestion = createBoolean("isSuggestion");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath password = createString("password");

    public final ListPath<com.locationbasedfoodieservice.review.entity.Review, com.locationbasedfoodieservice.review.entity.QReview> reviewList = this.<com.locationbasedfoodieservice.review.entity.Review, com.locationbasedfoodieservice.review.entity.QReview>createList("reviewList", com.locationbasedfoodieservice.review.entity.Review.class, com.locationbasedfoodieservice.review.entity.QReview.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

