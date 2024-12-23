package com.busanit501.springbootexam.repository;

import com.busanit501.springbootexam.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 검색,필터는 없이 간단한 페이징만 할것이므로 직접 작성
    @Query("select r from Review r where r.movie.mno = :mno")
    Page<Review> reviewList(Long mno, Pageable pageable);
    @Query("select r.rno from Review r where r.movie.mno = :mno")
    List<Long> rnoFromMno(Long mno);
}
