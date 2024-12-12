package com.busanit501.springbackend.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int pageSize; // 한번에 보여줄 페이지의 수
    private int last; // 마지막 페이지 번호
    private int start; // 현재 보여주는 첫 페이지 번호
    private int end; // 현재 보여주는 마지막 페이지 번호
    private boolean prev; // 이전 페이지 있으면 true
    private boolean next; // 이후 페이지 있으면 true
    private List<E> dtoList; // 현재 페이지에 보여줄 행들
    private int total;

    @Builder(builderMethodName = "response")
    public PageResponseDTO(List<E> dtoList, int total, PageRequestDTO pageRequestDTO) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.pageSize = pageRequestDTO.getPageSize();
        this.total = total;
        this.dtoList = dtoList;
        this.last = (int)Math.ceil((double) total /size);
        this.end = (int)Math.ceil((double) page /pageSize)*pageSize;
        this.end = Math.min(end, last);
        this.start = end-pageSize+1;
        this.prev = start > 1;
        this.next = total > end*size;
    }
}

