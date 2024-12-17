package com.busanit501.bootpractice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class PrRestController {

    @PostMapping("/rest")
    public String[] hello() {
        return new String[]{"aaa","bbb","ccc"};
    }
}
