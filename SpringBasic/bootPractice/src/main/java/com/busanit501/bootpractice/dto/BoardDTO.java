package com.busanit501.bootpractice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String author;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
