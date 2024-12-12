package com.busanit501.springbackend.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationVO {
    private int resId;
    private int movieId;
    private int userId;
    private LocalDate resdate;
}
