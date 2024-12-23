package com.busanit501.springbootexam.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MovieWithReviewCountDTO {
    private Long mno;
    private String title;
    private String author;
    private int reservation;
    private LocalDate releaseDate;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long reviewCount;
}
