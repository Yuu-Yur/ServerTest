package com.busanit501.bootpractice.dto;


import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodWithReplyImageDTO { // 전체 조회 시 사용할 DTO 전체 조회에서 보여주고 싶은 내용
    private Long fno;
    private String name;
    private String shop;
    private int price;
    private boolean revisit;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long replyCount;
    private List<FoodImageDTO> foodImages;
}
