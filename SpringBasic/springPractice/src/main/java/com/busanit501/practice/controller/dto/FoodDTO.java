package com.busanit501.practice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDTO {
    Long fno;
    @NotEmpty
    String title;
    @NotNull
    int price;
    int counter;
}
