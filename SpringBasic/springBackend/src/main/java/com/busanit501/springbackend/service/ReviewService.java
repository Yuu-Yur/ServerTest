package com.busanit501.springbackend.service;

import com.busanit501.springbackend.domain.ReviewVO;
import com.busanit501.springbackend.dto.MovieDTO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.dto.PageResponseDTO;
import com.busanit501.springbackend.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void register(ReviewDTO reviewDTO);
    void update(ReviewDTO reviewDTO);
    void delete(Long rid);
    PageResponseDTO<ReviewDTO> getPage(PageRequestDTO pageRequestDTO, MovieDTO movieDTO);
}
