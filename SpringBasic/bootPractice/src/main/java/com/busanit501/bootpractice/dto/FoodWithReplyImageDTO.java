package com.busanit501.bootpractice.dto;


import com.busanit501.bootpractice.domain.FoodImage;
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
public class FoodWithReplyImageDTO {
    private Long fno;
    private String name;
    private String shop;
    private int price;
    private boolean revisit = false;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private Long replyCount;
    private List<FoodImage> foodImages;
}
