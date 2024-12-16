package com.busanit501.bootpractice.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;
    private String type;
    private String keyword;
    private int price;

    public String[] getTypes() {
        if (type == null || type.isEmpty()) {
            return null;
        }
        //
        return type.split("");
    }
    // ...props 가변 parameter 여러개의 parameter 을 받을 수 있음
    public Pageable getPageable(String ...props) {
        Pageable pageable = PageRequest.of(this.page - 1, this.size
        , Sort.by(props).descending());
        return pageable;
    }
}
