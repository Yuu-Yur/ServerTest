package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.FoodWithReplyImageDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;

public interface FoodService {
    public void update(FoodDTO foodDTO);
    public void delete(Long fno);
    public void register(FoodDTO foodDTO);
    public FoodDTO getBoardById(Long fno);
    public PageResponseDTO<FoodWithReplyImageDTO> getPage(PageRequestDTO pageRequestDTO);
}
