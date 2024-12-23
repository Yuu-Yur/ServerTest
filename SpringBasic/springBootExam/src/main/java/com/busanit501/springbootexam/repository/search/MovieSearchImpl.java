package com.busanit501.springbootexam.repository.search;

import com.busanit501.springbootexam.domain.Movie;
import com.busanit501.springbootexam.domain.QMovie;
import com.busanit501.springbootexam.domain.QReview;
import com.busanit501.springbootexam.dto.MovieWithReviewCountDTO;
import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

@Log4j2
public class MovieSearchImpl extends QuerydslRepositorySupport implements MovieSearch {
    public MovieSearchImpl() {super(Movie.class);}
    @Override
    public Page<MovieWithReviewCountDTO> search(String[] types, String keyword, PageRequestDTO pageRequestDTO) {
        QMovie movie = QMovie.movie;
        QReview review = QReview.review;
        JPQLQuery<Movie> query = from(movie);
        query.leftJoin(review).on(review.movie.mno.eq(movie.mno));
        query.groupBy(movie);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (types != null && types.length > 0 && keyword != null) {
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(movie.title.contains(keyword));
                        break;
                    case "a":
                        booleanBuilder.or(movie.author.contains(keyword));
                        break;
                    case "r":
                        booleanBuilder.or(movie.reservation.gt(Integer.parseInt(keyword)));
                        break;
                }
            }
        }
        if (pageRequestDTO.isReleased()) {
            booleanBuilder.and(movie.releaseDate.loe(LocalDate.now()));
        } else {
            booleanBuilder.and(movie.releaseDate.gt(LocalDate.now()));
        }
        query.where(booleanBuilder);

        JPQLQuery<MovieWithReviewCountDTO> dtoQuery = query.select(Projections.bean(MovieWithReviewCountDTO.class,
                movie.mno,
                movie.title,
                movie.author,
                movie.reservation,
                movie.releaseDate,
                movie.regDate,
                movie.modDate,
                review.count().as("reviewCount")
                ));
        Pageable pageable = pageRequestDTO.getPageable("mno");
        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<MovieWithReviewCountDTO> list = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();
        return new PageImpl<MovieWithReviewCountDTO>(list, pageable, total);
    }
}
