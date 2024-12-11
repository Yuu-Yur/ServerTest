package com.busanit501.bootpractice.repository;

import com.busanit501.bootpractice.domain.Board;
import com.busanit501.bootpractice.domain.FoodBoard;
import com.busanit501.bootpractice.repository.search.FoodSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodBoard,Long>, FoodSearch {
    // 1. 쿼리스트링 implements
    Page<FoodBoard> findByNameContainingOrderByFnoDesc(String name, Pageable pageable);
    // 2. @Query annotation 사용 직접 sql 작성
    @Query(" select b from FoodBoard b where b.name like concat('%',:keyword) ")
    Page<FoodBoard> findByKeyword(String keyword, Pageable pageable);
    // 3. querydsl -> FoodSearch 확인
}
