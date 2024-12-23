package com.busanit501.springbootexam.service;

import com.busanit501.springbootexam.domain.Movie;
import com.busanit501.springbootexam.dto.MovieWithReviewCountDTO;
import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.dto.PageResponseDTO;
import com.busanit501.springbootexam.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(MovieWithReviewCountDTO movieWithReviewCountDTO) {
        Movie mapperResult = modelMapper.map(movieWithReviewCountDTO, Movie.class);
        Movie result = movieRepository.save(mapperResult);
        return result.getMno();
    }

    @Override
    public Long update(MovieWithReviewCountDTO movieWithReviewCountDTO) {
        Movie mapperResult = modelMapper.map(movieWithReviewCountDTO, Movie.class);
        Movie result = movieRepository.save(mapperResult);
        return result.getMno();
    }

    @Override
    public Long delete(Long mno) {
        movieRepository.deleteById(mno);
        return mno;
    }

    @Override
    public MovieWithReviewCountDTO movieDetail(Long mno) {
        Movie movie = movieRepository.findById(mno).orElseThrow();
        MovieWithReviewCountDTO result = modelMapper.map(movie, MovieWithReviewCountDTO.class);
        return result;
    }

    @Override
    public PageResponseDTO<MovieWithReviewCountDTO> getMoviesWithReviewCount(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Page<MovieWithReviewCountDTO> result = movieRepository.search(types,keyword,pageRequestDTO);
        return PageResponseDTO.<MovieWithReviewCountDTO>builder()
                .dtoList(result.getContent())
                .pageRequestDTO(pageRequestDTO)
                .total((int) result.getTotalElements()).build();
    }
}
