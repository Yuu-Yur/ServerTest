package com.busanit501.springbackend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Long rid;
    private String userName;
    private String title;
    private String content;
}
