package com.busanit501.practice.sample;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


// 컨트롤러 역할 @Controller
// 서비스 역할 @Service
// DAO 역할 @Repository
@Component // 일반 역할 객체 타입
@ToString
public class SampleService {
//    기존이라면
//    SampleDAO sampleDao = new SampleDAO();
//    로 사용할텐데
    @Autowired
    private SampleDAO sampleDAO;
}
