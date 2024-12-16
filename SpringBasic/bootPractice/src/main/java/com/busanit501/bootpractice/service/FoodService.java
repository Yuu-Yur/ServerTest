package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.dto.BoardDTO;
import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import org.springframework.data.domain.Page;

public interface FoodService {
    public void update(FoodDTO foodDTO);
    public void delete(Long fno);
    public void register(FoodDTO foodDTO);
    public FoodDTO getBoardById(Long fno);
    public PageResponseDTO<FoodDTO> getPage(PageRequestDTO pageRequestDTO);
}
