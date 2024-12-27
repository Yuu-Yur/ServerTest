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
public class FoodDTO { // 하나 조회 시 사용 할 DTO
    private Long fno;
    private String name;
    private String shop;
    private int price;
    private boolean revisit;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long replyCount;
    private List<String> fileNames;
}
