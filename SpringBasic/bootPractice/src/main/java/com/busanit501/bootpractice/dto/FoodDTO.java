package com.busanit501.bootpractice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    private Long fno;
    private String name;
    private String shop;
    private int price;
    private boolean revisit = false;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long replyCount;
}
