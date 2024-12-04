package com.busanit501.practice.controller.dto;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodVO {
    Long fno;
    String title;
    int price;
    int counter;
}
