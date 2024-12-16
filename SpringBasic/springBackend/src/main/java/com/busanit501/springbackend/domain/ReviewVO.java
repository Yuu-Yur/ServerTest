package com.busanit501.springbackend.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewVO {
    private Long rid;
    private String userName;
    private String title;
    private String content;
}