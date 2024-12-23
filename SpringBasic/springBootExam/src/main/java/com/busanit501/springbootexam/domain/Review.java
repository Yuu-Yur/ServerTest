package com.busanit501.springbootexam.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "Review", indexes = {
        @Index(name = "idx_review_movie_mno", columnList = "movie_mno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "movie")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    @NotEmpty
    private String reviewText;
    @NotEmpty
    private String reviewer;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
