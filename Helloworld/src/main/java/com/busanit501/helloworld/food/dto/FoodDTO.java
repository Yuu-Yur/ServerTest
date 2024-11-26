package com.busanit501.helloworld.food.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    int fno;
    String title;
    int price;
    int counter;
}
