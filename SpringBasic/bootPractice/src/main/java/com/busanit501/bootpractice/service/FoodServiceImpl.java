package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.domain.FoodBoard;
import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    @Override
    public void update(FoodDTO foodDTO) {
        FoodBoard result = foodRepository.findById(foodDTO.getFno()).orElseThrow();
        result.changeNSPR(foodDTO.getName(), foodDTO.getShop(), foodDTO.getPrice(), foodDTO.isRevisit());
        foodRepository.save(result);
    }

    @Override
    public void delete(Long fno) {
        foodRepository.deleteById(fno);
    }

    @Override
    public void register(FoodDTO foodDTO) {
        foodRepository.save(modelMapper.map(foodDTO, FoodBoard.class));
    }

    @Override
    public FoodDTO getBoardById(Long fno) {
        FoodBoard result = foodRepository.findById(fno).orElseThrow();
        return modelMapper.map(result, FoodDTO.class);
    }

    @Override
    public PageResponseDTO<FoodDTO> getPage(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable();
        Page<FoodBoard> result = foodRepository.search(types, keyword, pageable);
        List<FoodDTO> dtoList = result.getContent()//List FB
                .stream()// Stream FB  
                // stream.map  stream 내의 요소들에 특정 함수 적용
                .map(foodBoard -> modelMapper.map(foodBoard, FoodDTO.class)) // Stream FDTO
                .collect(Collectors.toList()); // List<FoodDTO>
        return PageResponseDTO.<FoodDTO>builder()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total((int)result.getTotalElements())
                .build();
    }
}
