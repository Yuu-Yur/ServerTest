package com.busanit501.springbackend.service;

import com.busanit501.springbackend.domain.MovieVO;
import com.busanit501.springbackend.dto.MovieDTO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.dto.PageResponseDTO;
import com.busanit501.springbackend.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieMapper movieMapper;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<MovieDTO> getListNowPast(PageRequestDTO pageRequestDTO) {
        log.info("pageRequestDTO: " + pageRequestDTO);
        int total = movieMapper.count(pageRequestDTO);
        log.info("total : " + total);
        List<MovieDTO> dtoList = movieMapper.selectPageNow(pageRequestDTO).stream().map(vo -> modelMapper.map(vo,MovieDTO.class)).collect(Collectors.toList());
        log.info("dtoList : " + dtoList);
        PageResponseDTO<MovieDTO> result = PageResponseDTO.<MovieDTO>response()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
        log.info("result : " + result);
         return result;
    }

    @Override
    public PageResponseDTO<MovieDTO> getListFuture(PageRequestDTO pageRequestDTO) {
        List<MovieDTO> dtoList = movieMapper.selectPageFuture(pageRequestDTO).stream().map(vo -> modelMapper.map(vo,MovieDTO.class)).collect(Collectors.toList());
        int total = movieMapper.count(pageRequestDTO);
        return PageResponseDTO.<MovieDTO>response()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public MovieDTO getOne(Long mid) {
        return modelMapper.map(movieMapper.selectById(mid),MovieDTO.class);
    }

    @Override
    public void update(MovieDTO movieDTO) {
        movieMapper.update(modelMapper.map(movieDTO, MovieVO.class));
    }

    @Override
    public void delete(Long mid) {
        movieMapper.deleteById(mid);
    }
}
