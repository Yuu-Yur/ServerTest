package com.busanit501.bootpractice.repository.search;

import com.busanit501.bootpractice.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
