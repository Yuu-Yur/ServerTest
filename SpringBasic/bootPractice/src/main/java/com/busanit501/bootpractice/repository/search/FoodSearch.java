package com.busanit501.bootpractice.repository.search;

import com.busanit501.bootpractice.dto.FoodWithReplyImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodSearch {
    Page<FoodWithReplyImageDTO> search(String[] types, String keyword, Pageable pageable);
}
