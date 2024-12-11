package com.busanit501.bootpractice.repository.search;

import com.busanit501.bootpractice.domain.FoodBoard;
import com.busanit501.bootpractice.domain.QFoodBoard;
import com.querydsl.core.BooleanBuilder;
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
    public Page<FoodBoard> search(String[] types, String keyword, int price, Pageable pageable) {
        QFoodBoard foodBoard = QFoodBoard.foodBoard;
        JPQLQuery<FoodBoard> query = from(foodBoard);
        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "name":
                        booleanBuilder.or(foodBoard.name.contains(keyword));
                        break;
                    case "shop":
                        booleanBuilder.or(foodBoard.shop.contains(keyword));
                        break;
                    case "price":
                        booleanBuilder.or(foodBoard.price.gt(price));
                        break;
                }
            }
            query.where(booleanBuilder);
        }
        this.getQuerydsl().applyPagination(pageable, query);
        List<FoodBoard> list = query.fetch();
        Long total = query.fetchCount();
        return new PageImpl<FoodBoard>(list, pageable, total);
    }
}
