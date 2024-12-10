package com.busanit501.practice.controller.dto;

import com.busanit501.practice.mapper.TodoMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int skip;
    private int pageSize; // 한번에 보여줄 페이지의 수
    private int last; // 마지막 페이지 번호
    private int start; // 현재 보여주는 첫 페이지 번호
    private int end; // 현재 보여주는 마지막 페이지 번호
    private boolean prev; // 이전 페이지 있으면 true
    private boolean next; // 이후 페이지 있으면 true
    private List<E> dtoList; // 현재 페이지에 보여줄 행들

    private TodoMapper todoMapper;

    @Builder(builderMethodName = "response")
    public PageResponseDTO(List<E> dtoList, int total, PageRequestDTO pageRequestDTO) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.pageSize = pageRequestDTO.getPageSize();
        this.dtoList = dtoList;
        this.last = (int)Math.ceil(total/this.size);
        this.end = (int)Math.ceil(this.page/this.pageSize)*this.pageSize;
        this.end = this.end > this.last ? this.last : this.end;
        this.start = this.end-this.pageSize+1;
        this.prev = this.start > 1;
        this.next = total > this.end*this.size;
    }
}
// 같은 DTO 지만  method 사용으로 구분할 수 있음
// PageDTO.response.builder , PageDTO.request.builder
