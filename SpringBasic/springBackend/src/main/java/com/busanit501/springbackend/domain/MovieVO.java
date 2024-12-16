package com.busanit501.springbackend.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieVO {
    private Long mid;
    private String title;
    private int reservation;
    private LocalDate releaseDate;
}
