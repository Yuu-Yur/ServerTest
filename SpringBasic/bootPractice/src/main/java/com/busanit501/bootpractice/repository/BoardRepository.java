package com.busanit501.bootpractice.repository;

import com.busanit501.bootpractice.domain.Board;
import com.busanit501.bootpractice.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// JpaRepository 는 기본 쿼리 메서드를 사용하기 위함
// Querydsl 이용 시 만든 interface 상속 이것은 Querydsl 기능을 사용하기 위함

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch{
// DAO
    // 1. 쿼리스트링
    Page<Board> findByTitleContaining(String title, Pageable pageable);

    // 2. @Query annotation
    // 모든 DB 에 사용 가능   여기에 쓰인 nativeQuery 는 해당 DB 의 언어로만 사용하겠다 따라서 sql 로 작성해야함
    @Query(value = "select * from board where title like concat('%',:keyword,'%')",nativeQuery = true)
    Page<Board> findByKeyword(String keyword, Pageable pageable);
    
    // 3. Querydsl
    // 여기에 따로 쓸 필요 없음
    // BoardSearch 를 상속

}
