package com.busanit501.springbackend.service;

import com.busanit501.springbackend.domain.MovieVO;
import com.busanit501.springbackend.dto.MovieDTO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.dto.PageResponseDTO;

public interface MovieService {
    PageResponseDTO<MovieDTO> getPage(PageRequestDTO requestDTO);

    MovieDTO getOne(Long mid);

    void update(MovieDTO movieDTO);
    void delete(Long mid);
    void register(MovieDTO movieDTO);
}
