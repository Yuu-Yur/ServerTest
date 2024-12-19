package com.busanit501.springbackend.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Long rid;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
}
