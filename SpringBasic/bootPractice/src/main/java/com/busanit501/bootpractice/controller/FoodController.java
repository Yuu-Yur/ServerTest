package com.busanit501.bootpractice.controller;

import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.FoodWithReplyImageDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/list")
    public String list(Model model, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        // 일단 페이지로 가게해두고 /food/list
        // html 작업 후 돌아오기
        PageResponseDTO<FoodWithReplyImageDTO> responseDTO = foodService.getPage(pageRequestDTO);
        model.addAttribute("responseDTO" , responseDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/food/list";
    }
    @GetMapping("/read")
    public String read(Long fno,
                     @Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult,
                     RedirectAttributes redirectAttributes,
                     Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(bindingResult);
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/food/list";
        }
        FoodDTO result = foodService.getBoardById(fno);
        model.addAttribute("dto" , result);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/food/read";


    }
}
