package com.locationbasedfoodieservice.rawrestaurant.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRawRestaurant is a Querydsl query type for RawRestaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRawRestaurant extends EntityPathBase<RawRestaurant> {

    private static final long serialVersionUID = 1996810311L;

    public static final QRawRestaurant rawRestaurant = new QRawRestaurant("rawRestaurant");

    public final StringPath bizplcNm = createString("bizplcNm");

    public final StringPath bsnsiteCircumfrDivNm = createString("bsnsiteCircumfrDivNm");

    public final StringPath bsnStateNm = createString("bsnStateNm");

    public final StringPath clsbizDe = createString("clsbizDe");

    public final NumberPath<Integer> femaleEnflpsnCnt = createNumber("femaleEnflpsnCnt", Integer.class);

    public final StringPath gradDivNm = createString("gradDivNm");

    public final StringPath gradFacltDivNm = createString("gradFacltDivNm");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath licensgDe = createString("licensgDe");

    public final NumberPath<Double> locplcAr = createNumber("locplcAr", Double.class);

    public final NumberPath<Integer> maleEnflpsnCnt = createNumber("maleEnflpsnCnt", Integer.class);

    public final StringPath multiUseBizestblYn = createString("multiUseBizestblYn");

    public final StringPath refineLotnoAddr = createString("refineLotnoAddr");

    public final StringPath refineRoadnmAddr = createString("refineRoadnmAddr");

    public final NumberPath<Double> refineWgs84Lat = createNumber("refineWgs84Lat", Double.class);

    public final NumberPath<Double> refineWgs84Logt = createNumber("refineWgs84Logt", Double.class);

    public final StringPath refineZipCd = createString("refineZipCd");

    public final StringPath sanittnBizcondNm = createString("sanittnBizcondNm");

    public final StringPath sanittnIndutypeNm = createString("sanittnIndutypeNm");

    public final StringPath sigunCd = createString("sigunCd");

    public final StringPath sigunNm = createString("sigunNm");

    public final NumberPath<Integer> totEmplyCnt = createNumber("totEmplyCnt", Integer.class);

    public final NumberPath<Double> totFacltScale = createNumber("totFacltScale", Double.class);

    public final NumberPath<Integer> yy = createNumber("yy", Integer.class);

    public QRawRestaurant(String variable) {
        super(RawRestaurant.class, forVariable(variable));
    }

    public QRawRestaurant(Path<? extends RawRestaurant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRawRestaurant(PathMetadata metadata) {
        super(RawRestaurant.class, metadata);
    }

}

