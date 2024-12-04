package com.busanit501.practice.service;

import com.busanit501.practice.controller.dto.FoodDTO;
import com.busanit501.practice.controller.dto.FoodVO;
import com.busanit501.practice.mapper.FoodMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class FoodServiceImpl implements FoodService {
    private final ModelMapper modelMapper;
    private final FoodMapper foodMapper;

    @Override
    public void register(@Valid FoodDTO foodDTO) {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodMapper.insert (foodVO);
        log.info ("S에서 " + foodVO + "insert");
    }

    @Override
    public List<FoodDTO> loadList() {
        List<FoodDTO> foodDTOList = foodMapper.selectAll().stream().map(vo -> modelMapper.map(vo, FoodDTO.class)).collect(Collectors.toList());
        log.info ("S에서 select");
        return foodDTOList.isEmpty() ? null : foodDTOList;
    }

    @Override
    public void edit(FoodDTO foodDTO) {
        foodMapper.update(modelMapper.map(foodDTO,FoodVO.class));
    }

    @Override
    public void delete(Long fno) {
        foodMapper.delete(fno);
    }

    @Override
    public FoodDTO loadDetail(Long fno) {
        return modelMapper.map(foodMapper.selectByFno(fno),FoodDTO.class);
    }
}
