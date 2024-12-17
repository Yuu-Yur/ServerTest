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
    public PageResponseDTO<MovieDTO> getPage(PageRequestDTO pageRequestDTO) {
        int total = movieMapper.count(pageRequestDTO);
        List<MovieDTO> dtoList = movieMapper.selectPage(pageRequestDTO).stream().map(vo -> modelMapper.map(vo,MovieDTO.class)).collect(Collectors.toList());
        PageResponseDTO<MovieDTO> result = PageResponseDTO.<MovieDTO>response()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();
         return result;
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
    public void delete(Long mid) { movieMapper.deleteById(mid); }

    @Override
    public void register(MovieDTO movieDTO) { movieMapper.insert(modelMapper.map(movieDTO, MovieVO.class)); }
}
