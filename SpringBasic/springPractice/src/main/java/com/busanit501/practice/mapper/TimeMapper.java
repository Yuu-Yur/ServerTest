package com.busanit501.practice.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
    // 방법1 mapper interface에서 annotation 이용 db호출
    @Select("select now()")
    String getNow();
    // 방법2 SQL문장을 분리한 xml파일
}
