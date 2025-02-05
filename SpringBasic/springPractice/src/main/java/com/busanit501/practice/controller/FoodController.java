package com.busanit501.practice.controller;

import com.busanit501.practice.controller.dto.FoodDTO;
import com.busanit501.practice.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/list")
    public void list(Model model) {
        List<FoodDTO> foodDTOList = foodService.loadList();
        model.addAttribute("foodDTOList", foodDTOList);
        log.info("C에서 " + foodDTOList.size() + "개의 목록 줌");
    }

    @GetMapping("/reg")
    public void reg() {
    }
    @PostMapping("/reg")
    public String regPost(@Valid FoodDTO foodDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("C에서" + foodDTO + "를 S로");
        if (bindingResult.hasErrors()) {
            log.info("유효성 검사 실패");
            redirectAttributes.addFlashAttribute("errors", bindingResult);
            return "redirect:/food/reg";
        }
        foodService.register(foodDTO);
        return "redirect:/food/list";
    }

    @GetMapping("/read")
    public void read(Long fno,Model model) {
        FoodDTO foodDTO = foodService.loadDetail(fno);
        model.addAttribute("foodDTO", foodDTO);
    }

    @PostMapping("/delete")
    public String delete(Long fno) {
        foodService.delete(fno);
        return "redirect:/food/list";
    }

    @GetMapping("/edit")
    public void edit(Long fno, Model model) {
        FoodDTO foodDTO = foodService.loadDetail(fno);
        model.addAttribute("foodDTO", foodDTO);
    }
    @PostMapping("/edit")
    public String editPost(@Valid FoodDTO foodDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getFieldError());
            return "redirect:/food/edit?fno=" + foodDTO.getFno();
        }
        foodService.edit(foodDTO);
        return "redirect:/food/read?fno=" + foodDTO.getFno();
    }
}
