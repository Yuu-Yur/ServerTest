package com.busanit501.springbackend.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private int revId;
    private String userName;
    private String movieTitle;
    private String content;
}
