package com.busanit501.springbootexam.repository;

import com.busanit501.springbootexam.domain.Movie;
import com.busanit501.springbootexam.repository.search.MovieSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieSearch {
    //기본 crud 는 JpaRepository 에서 상속받음
    //검색, 필터 는 MovieSearch 에서 상속받음
}
