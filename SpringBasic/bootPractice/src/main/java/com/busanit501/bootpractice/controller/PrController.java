package com.busanit501.bootpractice.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class PrController {
    @GetMapping("/pr")
    public void pr(Model model) {
        model.addAttribute("msg", "list");
        model.addAttribute("msg2", "register");
    }
}
