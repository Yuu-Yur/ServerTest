package com.busanit501.bootpractice.repository;

import com.busanit501.bootpractice.domain.Board;
import com.busanit501.bootpractice.domain.FoodBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodBoard,Long> {
    //DAO
}
