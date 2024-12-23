package com.busanit501.springbootexam.service;

import com.busanit501.springbootexam.dto.MovieWithReviewCountDTO;
import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.dto.PageResponseDTO;

public interface MovieService {
    public Long register(MovieWithReviewCountDTO movieWithReviewCountDTO);
    public Long update(MovieWithReviewCountDTO movieWithReviewCountDTO);
    public Long delete(Long mno);
    public MovieWithReviewCountDTO movieDetail(Long mno);
    public PageResponseDTO<MovieWithReviewCountDTO> getMoviesWithReviewCount(PageRequestDTO pageRequestDTO);
}
