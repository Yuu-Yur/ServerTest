package com.busanit501.springbackend.service;

import com.busanit501.springbackend.dto.MovieDTO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.dto.PageResponseDTO;

public interface MovieService {
    PageResponseDTO<MovieDTO> getListNowPast(PageRequestDTO requestDTO);

    PageResponseDTO<MovieDTO> getListFuture(PageRequestDTO requestDTO);

    MovieDTO getOne(Long mid);

    void update(MovieDTO movieDTO);
    void delete(Long mid);
}
