package com.busanit501.springbackend.mapper;

import com.busanit501.springbackend.domain.MovieVO;
import com.busanit501.springbackend.dto.PageRequestDTO;

import java.util.List;

public interface MovieMapper {
    List<MovieVO> selectPage(PageRequestDTO requestDTO);
    int count(PageRequestDTO pageRequestDTO);
    MovieVO selectById(Long mid);
    void insert(MovieVO movieVO);
    void update(MovieVO movieVO);
    void deleteById(Long mid);
}
