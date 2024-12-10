package com.busanit501.bootpractice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class prRestController {

    @GetMapping("/rest")
    public String hello() {return "yet";}
}
