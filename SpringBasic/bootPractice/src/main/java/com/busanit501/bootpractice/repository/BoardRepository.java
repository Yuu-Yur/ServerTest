package com.busanit501.bootpractice.repository;

import com.busanit501.bootpractice.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
// DAO
}
