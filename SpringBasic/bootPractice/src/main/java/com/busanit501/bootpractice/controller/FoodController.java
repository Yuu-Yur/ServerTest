package com.busanit501.bootpractice.controller;

import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.FoodWithReplyImageDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.service.FoodReplyService;
import com.busanit501.bootpractice.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;
    private final FoodReplyService foodReplyService;

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

    @PreAuthorize("hasRole('ADMIN')") // 미리 권한 지정 PreAuthorize admin 만
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
    @GetMapping("/update")
    public String update(Long fno, Model model, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        FoodDTO result = foodService.getBoardById(fno);
        model.addAttribute("dto", result);
        return "/food/update";
    }
    @PostMapping("/update")
    public String update(@Valid FoodDTO foodDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO, Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update?fno="+foodDTO.getFno() + "&" + pageRequestDTO.getLink();
        }
        foodService.update(foodDTO);
        redirectAttributes.addFlashAttribute("result", foodDTO.getFno());
        return "redirect:/food/update?fno="+foodDTO.getFno() + "&" + pageRequestDTO.getLink();
    }
    @PostMapping("/delete")
    public String delete(Long fno, RedirectAttributes redirectAttributes) {
        List<Long> rnoList = foodService.rnoByFno(fno);
        rnoList.forEach(rno -> {foodReplyService.delete(rno);});
        foodService.delete(fno);
        redirectAttributes.addFlashAttribute("result", fno);
        return "redirect:/food/list";
    }

    @PostMapping("/reg")
    public String reg(@Valid FoodDTO foodDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      PageRequestDTO pageRequestDTO,
                      Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/list?" + pageRequestDTO.getLink();
        }
        foodService.register(foodDTO);
        redirectAttributes.addFlashAttribute("result", foodDTO.getFno());
        return "redirect:/food/list";
    }
}
