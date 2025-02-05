package com.busanit501.springbackend.service;

import com.busanit501.springbackend.domain.ReviewVO;
import com.busanit501.springbackend.dto.ReviewDTO;
import com.busanit501.springbackend.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(ReviewDTO reviewDTO) {
        reviewMapper.insert(modelMapper.map(reviewDTO,ReviewVO.class));
    }

    @Override
    public void update(ReviewDTO reviewDTO) {
        reviewMapper.update(modelMapper.map(reviewDTO,ReviewVO.class));
    }

    @Override
    public void delete(Long rid) {
        reviewMapper.delete(rid);
    }

    @Override
    public List<ReviewDTO> getReview(String title) {
        List<ReviewDTO> dtoList = reviewMapper.select(title)
                .stream()
                .map(reviewVO -> modelMapper.map(reviewVO,ReviewDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
}
