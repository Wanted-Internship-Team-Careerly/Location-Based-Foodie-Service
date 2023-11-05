package com.locationbasedfoodieservice.sigungu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSigungu is a Querydsl query type for Sigungu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSigungu extends EntityPathBase<Sigungu> {

    private static final long serialVersionUID = 154704967L;

    public static final QSigungu sigungu = new QSigungu("sigungu");

    public final StringPath doSi = createString("doSi");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lon = createNumber("lon", Double.class);

    public final StringPath sgg = createString("sgg");

    public QSigungu(String variable) {
        super(Sigungu.class, forVariable(variable));
    }

    public QSigungu(Path<? extends Sigungu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSigungu(PathMetadata metadata) {
        super(Sigungu.class, metadata);
    }

}

