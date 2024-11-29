package com.busanit501.practice.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@ToString
//@RequiredArgsConstructor
public class SampleService {
//    기존이라면
//    SampleDAO sampleDao = new SampleDAO();
//    로 사용할텐데
//    1)필드 주입
//    @Autowired
//    private SampleDAO sampleDAO;
//    2)생성자 주입
//    2-1)기본 생성자 주입
    private final SampleDAO sampleDAO;
    public SampleService(SampleDAO sampleDAO) {
        this.sampleDAO = sampleDAO;
    }
//    2-2)lombok 애너테이션 이용
//    annotation RAC RequiredArgsConstructor 필요
//    private final SampleDAO sampleDAO;


}
