package com.busanit501.springbootexam.serviceTest;

import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.dto.PageResponseDTO;
import com.busanit501.springbootexam.dto.ReviewDTO;
import com.busanit501.springbootexam.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReviewServiceTest {
    @Autowired
    private ReviewService reviewService;

    @Test
    public void insertReview() {
            reviewService.register(ReviewDTO.builder()
                    .mno(1L)
                    .reviewer("ReviewServiceTest")
                    .reviewText("리뷰서비스단위테스트")
                    .build());
    }

    @Test
    public void updateReview() {
        reviewService.edit(ReviewDTO.builder()
                .rno(20L)
                .mno(5L)
                .reviewer("ReviewServiceTestUpdate")
                .reviewText("리뷰서비스단위테스트수정2")
                .build());
    }

    @Test
    public void deleteReview() {
        reviewService.delete(22L);
    }

    @Test
    public void loadReview() {
        PageResponseDTO<ReviewDTO> result = reviewService.getReviewList(1L, PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build());
        log.info(result);
    }
}
