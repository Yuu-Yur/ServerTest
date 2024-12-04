package com.busanit501.practice.service;

import com.busanit501.practice.controller.dto.FoodDTO;

import java.util.List;

public interface FoodService {
    void register(FoodDTO foodDTO);
    List<FoodDTO> loadList();
    void edit(FoodDTO foodDTO);
    void delete(Long fno);
    FoodDTO loadDetail(Long fno);

}