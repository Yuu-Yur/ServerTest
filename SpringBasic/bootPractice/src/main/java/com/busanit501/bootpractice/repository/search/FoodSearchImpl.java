package com.busanit501.bootpractice.repository.search;

import com.busanit501.bootpractice.domain.FoodBoard;
import com.busanit501.bootpractice.domain.QFoodBoard;
import com.busanit501.bootpractice.domain.QFoodReply;
import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.FoodWithReplyImageDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl() {
        super(FoodBoard.class);
    }

    @Override
    public Page<FoodWithReplyImageDTO> search(String[] types, String keyword, Pageable pageable) {
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
        // 검색,필터 조건이 추가되어 있는 entity 클래스의 query 를 dto 클래스의 query 로 형변환을 할건데
        // 방법1 즉시 변환-projections.bean
        // entity 클래스의 쿼리 select Projections.bean
        // FoodDTO.class 로 형변환
        /*JPQLQuery<FoodDTO> dtoQuery = query.select(Projections.bean(FoodDTO.class,
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
        this.getQuerydsl().applyPagination(pageable, dtoQuery);*/
        // 방법2 tuple 사용
        // tuple 이란 배열과 비슷함
        // 여기선 foodBoard 와 replyCount 를 select 하는 query 를 tupleJPQLQuery 라고 함
        JPQLQuery<Tuple> tupleJPQLQuery = query.select(
                foodBoard, foodReply.countDistinct()
        );
        List<Tuple> tupleList = tupleJPQLQuery.fetch(); // 이 tupleJPQLQuery 의 반환을 Tuple 의 리스트로 받음
        // 이때 이 tupleList 의 안에는 tuple
        // 이 tuple 안에는 select 결과 FoodBoard 엔티티,ReplyCount 가 Long 타입으로 담겨있음
        // 이것을 DTO 로 형변환해줘야함
        List<FoodWithReplyImageDTO> dtoList = tupleList.stream().map(tuple -> {
            FoodBoard food = tuple.get(0, FoodBoard.class);
            Long count = tuple.get(1, Long.class);
            FoodWithReplyImageDTO dto = FoodWithReplyImageDTO.builder() // 여기에 tuple 에서 꺼낸걸 여러번써야하니 변수
                    .fno(food.getFno())
                    .name(food.getName())
                    .shop(food.getShop())
                    .price(food.getPrice())
                    .revisit(food.isRevisit())
                    .replyCount(count)
                    //regD, modD 생략
                    .build();
            return dto;
        }).collect(Collectors.toList());
//        List<FoodDTO> list = dtoQuery.fetch(); 리스트 만듬
//        long total = dtoQuery.fetchCount(); dtoQuery 가 아니라 tupleJPQLQuery 사용
//        return new PageImpl<FoodDTO>(list, pageable, total);
        return new PageImpl<FoodWithReplyImageDTO>(dtoList, pageable, tupleJPQLQuery.fetchCount());
    }
}
