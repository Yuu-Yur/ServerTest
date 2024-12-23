package com.busanit501.springbootexam.repositoryTest;

import com.busanit501.springbootexam.domain.Movie;
import com.busanit501.springbootexam.domain.Review;
import com.busanit501.springbootexam.repository.ReviewRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void testInsert() {
        LongStream.range(1, 501).forEach(i -> {
        Review review = Review.builder()
                .movie(Movie.builder()
                        .mno(i%100+1).build())
                .reviewer("단위테스트 리뷰어")
                .reviewText("단위테스트 리뷰" + i)
                .build();
        reviewRepository.save(review);
        });
    }
    @Test
    public void testRead() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());
        Page<Review> result = reviewRepository.reviewList(1L,pageable);
        log.info(result.getContent());
    }
}
