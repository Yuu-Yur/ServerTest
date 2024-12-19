package com.busanit501.bootpractice.repository.search;

import com.busanit501.bootpractice.domain.FoodBoard;
import com.busanit501.bootpractice.domain.QFoodBoard;
import com.busanit501.bootpractice.domain.QFoodReply;
import com.busanit501.bootpractice.dto.FoodDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl() {
        super(FoodBoard.class);
    }

    @Override
    public Page<FoodDTO> search(String[] types, String keyword, Pageable pageable) {
        QFoodBoard foodBoard = QFoodBoard.foodBoard;
        QFoodReply foodReply = QFoodReply.foodReply;
        JPQLQuery<FoodBoard> query = from(foodBoard);
        query.leftJoin(foodReply).on(foodReply.foodBoard.fno.eq(foodBoard.fno));
        query.groupBy(foodBoard);

        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "n":
                        booleanBuilder.or(foodBoard.name.contains(keyword));
                        break;
                    case "s":
                        booleanBuilder.or(foodBoard.shop.contains(keyword));
                        break;
                    case "p":
                        booleanBuilder.or(foodBoard.price.lt(Integer.parseInt(keyword)));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        query.orderBy(foodBoard.fno.desc());
        // 검색,필터 조건이 추가되어 있는
        // entity 클래스의 query 를 dto 클래스의 query 로 형변환을 할건데
                                   // entity 클래스의 쿼리 select Projections.bean
                                                                // FoodDTO.class 로 형변환
        JPQLQuery<FoodDTO> dtoQuery = query.select(Projections.bean(FoodDTO.class,
                foodBoard.fno,
                foodBoard.name,
                foodBoard.shop,
                foodBoard.price,
                foodBoard.revisit,
                foodBoard.regDate,
                foodBoard.modDate,
                // FoodDTo 에서 변수를 replyCount 라고 썼으므로
                foodReply.count().as("replyCount")
                ));
        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<FoodDTO> list = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();
        return new PageImpl<FoodDTO>(list, pageable, total);
    }
}
