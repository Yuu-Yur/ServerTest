package com.busanit501.bootpractice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
@RequestMapping("/todo")
public class ToDoController {
    @GetMapping("/list")
    public void list(Model model) {
        List<String> list = IntStream.range(1,6)
//                mapper int->Obj String에 넣기 위해
                .mapToObj(i -> "list" + i)
                //그리고 이렇게 만들어진 것을 list에 할당
                .collect(Collectors.toList());
        log.info(list + "list controller");
        model.addAttribute("list", list);
    }

    @GetMapping("/register")
    public void register(Model model) {
        int i = new Random().nextInt(10);
        boolean check = false;
        if (i>4) check = true;
        model.addAttribute("check", check);
        model.addAttribute("msg", "회원 가입");
    }
}
