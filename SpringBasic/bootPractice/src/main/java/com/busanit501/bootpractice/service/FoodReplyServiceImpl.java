package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.domain.FoodReply;
import com.busanit501.bootpractice.dto.FoodReplyDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.repository.FoodReplyRepository;
import com.busanit501.bootpractice.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodReplyServiceImpl implements FoodReplyService {
    private final FoodReplyRepository foodReplyRepository;
    private final ModelMapper modelMapper;
    private final FoodRepository foodRepository;

    @Override
    public Long update(FoodReplyDTO foodReplyDTO) {
        FoodReply reply = modelMapper.map(foodReplyDTO, FoodReply.class);
        reply.foodBoardSetter(foodRepository.findById(foodReplyDTO.getFno()).orElseThrow());
        FoodReply result = foodReplyRepository.save(reply);
        Long replyId = result.getReplyId();
        return replyId;
    }

    @Override
    public void delete(Long replyId) {
        foodReplyRepository.deleteById(replyId);
    }

    @Override
    public Long register(FoodReplyDTO foodReplyDTO) {
        FoodReply reply = modelMapper.map(foodReplyDTO, FoodReply.class);
        reply.foodBoardSetter(foodRepository.findById(foodReplyDTO.getFno()).orElseThrow());
        FoodReply result = foodReplyRepository.save(reply);
        Long replyId = result.getReplyId();
        return replyId;
    }

    @Override
    public FoodReplyDTO getFoodReplyById(Long replyId) {
        FoodReply foodReply = foodReplyRepository.findById(replyId).orElseThrow();
        FoodReplyDTO result = modelMapper.map(foodReply, FoodReplyDTO.class);
        result.setFno(foodReply.getFoodBoard().getFno());
        return result;
    }

    @Override
    public PageResponseDTO<FoodReplyDTO> getFoodReplyPage(Long fno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("replyId").descending());
        Page<FoodReply> result = foodReplyRepository.listofFoodReply(fno, pageable);
        List<FoodReplyDTO> dtoList = result.getContent().stream().map(foodReply -> {
            FoodReplyDTO frDTO = modelMapper.map(foodReply, FoodReplyDTO.class);
            frDTO.setFno(foodReply.getFoodBoard().getFno());
            return frDTO;
        }).toList();
        return PageResponseDTO.<FoodReplyDTO>builder()
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .pageRequestDTO(pageRequestDTO)
                .build();
    }
}
