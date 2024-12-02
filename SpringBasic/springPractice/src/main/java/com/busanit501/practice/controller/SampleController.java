package com.busanit501.practice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SampleController {
    // 1.화면제공(Get)
    // servlet-context 의 prefix, suffix 가 붙어 경로가 됨
    // return 이 없으므로 method 이름 hello 에 prefix, suffix를 붙임
    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }
    // 만약 문자열을 return 한다면 해당 문자열에 prefix suffix 가 붙어 경로가 됨
    @GetMapping("/hello2")
    public String hello2() {
        log.info("hello2");
        return "helloTest";
    }
    // void return -> prefix methodName suffix -> viewFileName
    // String return -> prefix returnString suffix -> viewFileName
}
