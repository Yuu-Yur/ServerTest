package com.busanit501.springbackend.domain;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewVO {
    private int revId;
    private String userName;
    private String movieTitle;
    private String content;
}
