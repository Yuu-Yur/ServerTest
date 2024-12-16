package com.busanit501.bootpractice.controller;

import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/list")
    public void list(Model model, PageRequestDTO pageRequestDTO) {
        // 일단 페이지로 가게해두고 /food/list
        // html 작업 후 돌아오기
        PageResponseDTO<FoodDTO> responseDTO = foodService.getPage(pageRequestDTO);
        model.addAttribute("responseDTO" , responseDTO);
    }
}
