package com.busanit501.springbackend.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {
    private int resId;
    private int movieId;
    private int userId;
    private LocalDate resdate;
}
