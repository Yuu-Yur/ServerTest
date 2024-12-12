package com.busanit501.springbackend.mapper;

import com.busanit501.springbackend.domain.MovieVO;
import com.busanit501.springbackend.dto.PageRequestDTO;

import java.util.List;

public interface MovieMapper {
    List<MovieVO> selectPageNow(PageRequestDTO requestDTO);
    List<MovieVO> selectPageFuture(PageRequestDTO requestDTO);
    int count(PageRequestDTO pageRequestDTO);
    MovieVO selectById(Long mid);
    void update(MovieVO movieVO);
    void deleteById(Long mid);
}
