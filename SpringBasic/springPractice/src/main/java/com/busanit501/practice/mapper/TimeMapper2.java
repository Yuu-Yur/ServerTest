package com.busanit501.practice.mapper;

public interface TimeMapper2 {
    // 방법2 SQL문장을 분리한 xml파일
    // 주의
    // mapper interface 이름과 xml 파일이름 동일 확인
    // method 이름과 xml 파일 id 동일 확인
    String getNow();
}
