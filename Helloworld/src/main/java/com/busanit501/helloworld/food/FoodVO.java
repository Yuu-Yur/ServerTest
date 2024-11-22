package com.busanit501.helloworld.food;

import lombok.*;

@Data
@Builder
public class FoodVO {
    int fno;
    String title;
    int price;
    int counter;
}
