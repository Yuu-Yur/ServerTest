package com.busanit501.springbootexam.repository.search;

import com.busanit501.springbootexam.dto.MovieWithReviewCountDTO;
import com.busanit501.springbootexam.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface MovieSearch {
    Page<MovieWithReviewCountDTO> search(String[] types, String keyword, PageRequestDTO pageRequestDTO);
}
