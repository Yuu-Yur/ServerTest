package com.busanit501.springbootexam.service;

import com.busanit501.springbootexam.domain.Movie;
import com.busanit501.springbootexam.domain.Review;
import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.dto.PageResponseDTO;
import com.busanit501.springbootexam.dto.ReviewDTO;
import com.busanit501.springbootexam.repository.MovieRepository;
import com.busanit501.springbootexam.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        Movie movie = movieRepository.findById(reviewDTO.getMno()).orElseThrow();
        review.setMovie(movie);
        Review result = reviewRepository.save(review);
        return result.getRno();
    }

    @Override
    public Long edit(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        Movie movie = movieRepository.findById(reviewDTO.getMno()).orElseThrow();
        review.setMovie(movie);
        Review result = reviewRepository.save(review);
        return result.getRno();
    }

    @Override
    public Long delete(Long rno) {
        reviewRepository.deleteById(rno);
        return rno;
    }

    @Override
    public PageResponseDTO<ReviewDTO> getReviewList(Long mno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("rno").descending());
        Page<Review> result = reviewRepository.reviewList(mno,pageable);
        List<ReviewDTO> dtoList = result.getContent().stream().map(review -> {
            ReviewDTO reviewDTO= modelMapper.map(review, ReviewDTO.class);
            reviewDTO.setMno(review.getMovie().getMno());
            return reviewDTO;
        }).collect(Collectors.toList());
        return PageResponseDTO.<ReviewDTO>builder()
                .total((int) result.getTotalElements())
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    @Override
    public List<Long> rnoListFromMno(Long mno) {
        List<Long> result = reviewRepository.rnoFromMno(mno);
        return result;
    }
}
