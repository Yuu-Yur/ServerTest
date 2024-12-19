package com.busanit501.springbackend.mapper;

import com.busanit501.springbackend.domain.ReviewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewMapper {
    void insert(ReviewVO reviewVO);
    void update(ReviewVO reviewVO);
    void delete(Long rid);
    List<ReviewVO> select(@Param("title") String title);
}
