package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.domain.FoodBoard;
import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.FoodWithReplyImageDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface FoodService {
    public void update(FoodDTO foodDTO);
    public void delete(Long fno);
    public void register(FoodDTO foodDTO);
    public FoodDTO getBoardById(Long fno);
    public PageResponseDTO<FoodWithReplyImageDTO> getPage(PageRequestDTO pageRequestDTO);
    public List<Long> rnoByFno(Long fno);
    default FoodBoard dtoToEntity(FoodDTO foodDTO) {
        FoodBoard foodBoard = FoodBoard.builder()
                .fno(foodDTO.getFno())
                .name(foodDTO.getName())
                .shop(foodDTO.getShop())
                .price(foodDTO.getPrice())
                .revisit(foodDTO.isRevisit())
                .build();
        if (foodDTO.getFileNames() != null) {
            // 들어온 DTO 에 file 이 있다면 이것은 uuid 와 name 의 합이므로 스플릿하고 entity 에 추가
            foodDTO.getFileNames().forEach(fileName -> {
                String[] split = fileName.split("_");
                foodBoard.addImage(split[0], split[1]);
            });
        }
        return foodBoard;
    };
    default FoodDTO entityToDto(FoodBoard foodBoard) {
        List<String> fileNames = foodBoard.getImageSet().stream().sorted().map(foodImage ->
                foodImage.getUuid() + "_" + foodImage.getFileName()
        ).collect(Collectors.toList());
        FoodDTO foodDTO = FoodDTO.builder()
                .fno(foodBoard.getFno())
                .name(foodBoard.getName())
                .shop(foodBoard.getShop())
                .price(foodBoard.getPrice())
                .revisit(foodBoard.isRevisit())
                .fileNames(fileNames)
                .build();
        return foodDTO;
    }
}
