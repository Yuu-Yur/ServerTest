package com.busanit501.practice.controller.dto;

import com.busanit501.practice.mapper.TodoMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDTO<E> {
    @Builder.Default
    @Min(value = 1)
    private int page = 1; // 현재 페이지

    @Builder.Default
    @Min(value = 1)
    @Max(value = 100)
    private int size = 10; // 한 페이지에 보여줄 행의 수

    public int getSkip() {
        return  (page - 1) * size;
    }



    private int pageSize; // 한번에 보여줄 페이지의 수
    private int last; // 마지막 페이지 번호
    private int start; // 현재 보여주는 첫 페이지 번호
    private int end; // 현재 보여주는 마지막 페이지 번호
    private boolean prev; // 이전 페이지 있으면 true
    private boolean next; // 이후 페이지 있으면 true
    private List<E> dtoList; // 현재 페이지에 보여줄 행들

    private TodoMapper todoMapper;


    // builder 생성자 만들 수 있음
    @Builder(builderMethodName = "request")
    public PageDTO(int page, int size, int pageSize) {
        this.page = page;
        this.size = size;
        this.pageSize = pageSize;
    }

    @Builder(builderMethodName = "response")
    public PageDTO(List<E> dtoList, int total, PageDTO pageDTO) {
        this.page = pageDTO.getPage();
        this.size = pageDTO.getSize();
        this.dtoList = dtoList;
        this.pageSize = pageDTO.getPageSize();
        this.last = (int)Math.ceil(total/size);
        this.end = (int)Math.ceil(page/pageSize)*pageSize;
        this.end = end > last ? last : end;
        this.start = end-pageSize+1;
        this.prev = start > 1;
        this.next = total > end*size;
    }
}
// 같은 DTO 지만  method 사용으로 구분할 수 있음
// PageDTO.response.builder , PageDTO.request.builder
//

