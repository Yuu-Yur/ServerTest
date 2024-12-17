package com.busanit501.springbackend.service;

import com.busanit501.springbackend.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void register(ReviewDTO reviewDTO);
    void update(ReviewDTO reviewDTO);
    void delete(Long rid);
    List<ReviewDTO> getReview(String title);
}
