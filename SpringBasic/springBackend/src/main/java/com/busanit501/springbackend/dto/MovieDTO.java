package com.busanit501.springbackend.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {
    private Long mid;
    private String title;
    private int reservation;
    private LocalDate releaseDate;
}
