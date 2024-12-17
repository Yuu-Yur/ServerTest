package com.busanit501.springbackend.mapper;

import com.busanit501.springbackend.domain.MovieVO;
import com.busanit501.springbackend.domain.ReviewVO;
import com.busanit501.springbackend.dto.PageRequestDTO;

import java.util.List;

public interface ReviewMapper {
    void insert(ReviewVO reviewVO);
    void update(ReviewVO reviewVO);
    void delete(Long rid);
    List<ReviewVO> select(PageRequestDTO pageRequestDTO, MovieVO movieVO);
    int count(PageRequestDTO pageRequestDTO, MovieVO movieVO);
}
