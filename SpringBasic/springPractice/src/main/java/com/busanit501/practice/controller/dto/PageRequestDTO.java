package com.busanit501.practice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageRequestDTO {
    @Builder.Default
    @Min(value = 1)
    private int page = 1;

    @Builder.Default
    @Min(value = 1)
    @Max(value = 100)
    private int size = 10;

    // getter 을 사용하면 필드에 없더라도  
    // xml 에서 EL 표기법으로 skip 을 호출할 수 있음
    public int getSkip() {
        return  (page - 1) * size;
    }
}

