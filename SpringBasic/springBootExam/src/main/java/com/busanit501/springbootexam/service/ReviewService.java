package com.busanit501.springbootexam.service;

import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.dto.PageResponseDTO;
import com.busanit501.springbootexam.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public Long register(ReviewDTO reviewDTO);
    public Long edit(ReviewDTO reviewDTO);
    public Long delete(Long rno);
    public PageResponseDTO<ReviewDTO> getReviewList(Long mno, PageRequestDTO pageRequestDTO);
    public List<Long> rnoListFromMno(Long mno);
}
