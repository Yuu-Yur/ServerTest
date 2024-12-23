package com.busanit501.springbootexam.repositoryTest;

import com.busanit501.springbootexam.domain.Movie;
import com.busanit501.springbootexam.dto.MovieWithReviewCountDTO;
import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.repository.MovieRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testInsert() {
        IntStream.range(1, 6).forEach(i -> {
        Movie movie = Movie.builder()
                .title("단위테스트" + i)
                .author("단위테스터")
                .reservation(100)
                .releaseDate(LocalDate.now()).build();
        movieRepository.save(movie);
        });

    }
    @Test
    public void testUpdate() {
        Movie movie = Movie.builder()
                .mno(3L)
                .title("단위테스트 수정")
                .author("단위테스터")
                .reservation(1)
                .releaseDate(LocalDate.now().plusDays(1))
                .build();
        Movie result = movieRepository.save(movie);
        log.info(result.getTitle());
    }
    @Test
    public void testRead() {
        String[] types = {"t"};
        String keyword = "트";
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        Page<MovieWithReviewCountDTO> result = movieRepository.search(types,keyword,pageRequestDTO);
        log.info(result.getTotalElements());
        log.info(result.getContent());
    }
    @Test
    public void testDelete() {
        LongStream.range(0, 20).forEach(i -> {
        movieRepository.deleteById(i);
        });
    }
}
