package com.busanit501.practice.controller;

import com.busanit501.practice.controller.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Log4j2
@Controller
@RequestMapping("/todo")
public class TodoController {
    @RequestMapping("/list")
    public String list() {
        log.info("list 화면 호출");
        return "list";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String reg() {
        log.info("reg 화면 호출");
        return "reg";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public void regP(TodoDTO todoDTO) {
        log.info("reg post DTO 받아오기" + todoDTO);
    }
}
