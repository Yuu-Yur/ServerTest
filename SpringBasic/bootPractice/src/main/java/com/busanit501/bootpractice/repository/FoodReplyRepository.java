package com.busanit501.bootpractice.repository;

import com.busanit501.bootpractice.domain.FoodReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodReplyRepository extends JpaRepository<FoodReply, Long> {
    @Query("select r from FoodReply r where r.foodBoard.fno = :fno")
    Page<FoodReply> listofFoodReply(Long fno, Pageable pageable);
}
