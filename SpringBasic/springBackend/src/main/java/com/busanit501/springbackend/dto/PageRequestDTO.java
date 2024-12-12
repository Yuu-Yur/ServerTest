package com.busanit501.springbackend.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    //목록 -> 상세보기 화면 이동시, 페이지 정보를 쿼리 스트링으로 전달하는 용도 멤버
    private String link;

    // 검색 또는 필터 관련 조건
    // 1, 검색어
    private String keyword;
    //2, 검색 유형 ,1) 제목,t 2) 작성자,w 3) 제목 + 작성자,tw
    private String[] types;
    //3, todo 완료 여부
    private boolean finished2;
    //4, 기한 1
    private LocalDate from;
    //4, 기한 2
    private LocalDate to;

    // getter 을 사용하면 필드에 없더라도
    // xml 에서 EL 표기법으로 skip 을 호출할 수 있음
    public int getSkip() {
        return  (page - 1) * size;
    }

    public String getLink() {
        StringBuilder builder = new StringBuilder();
        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);

        // 검색 및 필터 조건 추가 하기.

        // 이부분,  URLEncoding 부분 누락 . -> 수정
        if(keyword != null) {
            try {
                builder.append("&keyword="+ URLEncoder.encode(keyword, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
//          throw new RuntimeException(e);
                e.printStackTrace();
            }
        }

        //   list&page=7&size=10&finished=on
        if (finished2) {
            // finished ,  체크박스 이어서, 전달 받을 때 문자열 "on"
            builder.append("&finished2=on");
        }

        if (types != null && types.length > 0) {
            for (int i = 0; i < types.length; i++) {
                //   list&page=7&size=10&finished=on&types=t
                builder.append("&types=").append(types[i]);
            }
        }

        if(from != null){
            //   list&page=7&size=10&finished=on&types=t&from=2024-12-05
            builder.append("&from="+ from.toString());
        }
        if(to != null){
            //   list&page=7&size=10&finished=on&types=t&from=2024-1205&to=2024-12-06
            builder.append("&to="+ to.toString());
        }

        return builder.toString();
    }

    //검색에서, 작성자, 제목으로 체크박스 체크여부를 확인하는 기능.
    public boolean checkType(String type) {
        if (types == null || types.length == 0) {
            return false;
        }
        // Arrays.stream(types) : 배열을 의미, {"t","w"}
        // 배열중에서, 요소 하나씩 꺼내서 계속 비교해요. type과비교
        // type ,예를 들어 t로 왔다.
        return Arrays.stream(types).anyMatch(type::equals);
    }
}
