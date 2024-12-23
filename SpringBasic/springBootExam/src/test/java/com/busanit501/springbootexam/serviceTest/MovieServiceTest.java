package com.busanit501.springbootexam.serviceTest;

import com.busanit501.springbootexam.dto.MovieWithReviewCountDTO;
import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.dto.PageResponseDTO;
import com.busanit501.springbootexam.service.MovieService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MovieServiceTest {
    @Autowired
    private MovieService movieService;

    @Test
    public void regTest() {
        IntStream.range(0, 10).forEach(i -> {
            Long result = movieService.register(MovieWithReviewCountDTO.builder()
                    .title("movieServiceTest" + i)
                    .author("테스터")
                    .reservation(10 * i)
                    .releaseDate(LocalDate.now())
                    .build());
            log.info(result);
        });
    }

    @Test
    public void detailTest() {
        MovieWithReviewCountDTO result = movieService.movieDetail(10L);
        log.info(result);
    }

    @Test
    public void updateTest() {
        movieService.update(MovieWithReviewCountDTO.builder()
                .mno(10L)
                .title("movieServiceTestUpdate")
                .author("테스터")
                .reservation(99)
                .releaseDate(LocalDate.now())
                .build());
    }

    @Test
    public void deleteTest() {
        movieService.delete(15L);
    }

    @Test
    public void listTest() {
        PageResponseDTO<MovieWithReviewCountDTO> result = movieService.getMoviesWithReviewCount(PageRequestDTO.builder()
                .type("t")
                .keyword("2")
                .page(1)
                .size(10)
                .released(true)
                .build());
        log.info(result);
    }
}
