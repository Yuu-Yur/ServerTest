package com.busanit501.springbootexam.controller;

import com.busanit501.springbootexam.dto.MovieWithReviewCountDTO;
import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.dto.PageResponseDTO;
import com.busanit501.springbootexam.service.MovieService;
import com.busanit501.springbootexam.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
@Log4j2
public class MovieController {
    private final MovieService movieService;
    private final ReviewService reviewService;

    @GetMapping("/list")
    public String list(Model model, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        PageResponseDTO<MovieWithReviewCountDTO> pageResponseDTO = movieService.getMoviesWithReviewCount(pageRequestDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/movie/list";
    }
    @GetMapping("/read")
    public String read(Long mno, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes, Model model) {
        MovieWithReviewCountDTO result = movieService.movieDetail(mno);
        model.addAttribute("dto", result);
        return "/movie/read";
    }
    @GetMapping("/update")
    public String update(Long mno, Model model, PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        MovieWithReviewCountDTO result = movieService.movieDetail(mno);
        model.addAttribute("dto", result);
        return "/movie/update";
    }
    @PostMapping("/update")
    public String update(@Valid MovieWithReviewCountDTO movieWithReviewCountDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO, Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/movie/update?mno="+movieWithReviewCountDTO.getMno() + "&" + pageRequestDTO.getLink();
        }
        Long result = movieService.update(movieWithReviewCountDTO);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/movie/update?mno="+movieWithReviewCountDTO.getMno() + "&" + pageRequestDTO.getLink();
    }
    @PostMapping("/delete")
    public String delete(Long mno, RedirectAttributes redirectAttributes) {
        List<Long> rnoList = reviewService.rnoListFromMno(mno);
        rnoList.forEach(rno -> {reviewService.delete(rno);});
        Long result = movieService.delete(mno);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/movie/list";
    }
    @PostMapping("/reg")
    public String reg(@Valid MovieWithReviewCountDTO movieWithReviewCountDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      PageRequestDTO pageRequestDTO,
                      Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/movie/list?" + pageRequestDTO.getLink();
        }
        Long result = movieService.register(movieWithReviewCountDTO);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/movie/list";
    }
}
