package com.busanit501.springbackend.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PageRequestDTO {
    @Builder.Default
    @Min(value = 1)
    private int page = 1;

    @Builder.Default
    @Min(value = 1)
    @Max(value = 100)
    private int size = 8;

    @Builder.Default
    @Min(value = 5)
    @Max(value = 15)
    private int pageSize = 5;

    @Builder.Default
    @NotNull
    private boolean released = true;

    private String link;


    private String keyword;
    private LocalDate from;
    private LocalDate to;
    private String[] types;


    public int getSkip() {
        return  (page - 1) * size;
    }

    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);

        // 검색 키워드 link 에 추가
        if(keyword != null) {
            try {
                builder.append("&keyword="+ URLEncoder.encode(keyword, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // 검색 날짜 link 에 추가
        if(from != null){
            builder.append("&from="+ from.toString());
        }
        if(to != null){
            builder.append("&to="+ to.toString());
        }

        // 검색 타입 title 이랑 releaseDate
        if (types != null && types.length > 0) {
            for (int i = 0; i < types.length; i++) {
                builder.append("&types=").append(types[i]);
            }
        }
        return builder.toString();
    }
}
