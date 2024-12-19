package com.busanit501.bootpractice.repository.search;

import com.busanit501.bootpractice.domain.FoodBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodSearch {
    Page<FoodBoard> search(String[] types, String keyword, Pageable pageable);
}
