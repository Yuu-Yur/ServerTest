package com.busanit501.practice.mapper;


import com.busanit501.practice.controller.dto.FoodVO;
import com.busanit501.practice.controller.dto.PageRequestDTO;

import java.util.List;

public interface FoodMapper {
    List<FoodVO> selectAll();
    FoodVO selectByFno(Long fno);
    int insert(FoodVO food);
    int update(FoodVO food);
    int delete(Long fno);
    List<FoodVO> selectByPage(PageRequestDTO pageRequestDTO);
    int selectCount(PageRequestDTO pageRequestDTO);
}
