package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.dto.FoodReplyDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;

public interface FoodReplyService {
    public Long update(FoodReplyDTO foodReplyDTO);
    public void delete(Long replyId);
    public Long register(FoodReplyDTO foodReplyDTO);
    public FoodReplyDTO getFoodReplyById(Long replyId);
    public PageResponseDTO<FoodReplyDTO> getFoodReplyPage(Long fno , PageRequestDTO pageRequestDTO);
}
