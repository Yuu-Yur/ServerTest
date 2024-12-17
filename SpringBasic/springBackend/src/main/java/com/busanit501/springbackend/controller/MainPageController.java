package com.busanit501.springbackend.controller;

import com.busanit501.springbackend.dto.MovieDTO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.dto.PageResponseDTO;
import com.busanit501.springbackend.dto.ReviewDTO;
import com.busanit501.springbackend.service.MovieService;
import com.busanit501.springbackend.service.ReviewService;
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

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/movie")
public class MainPageController {
    private final MovieService movieService;
    private final ReviewService reviewService;

    @GetMapping("/main")
    public String main(@Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/movie/main";
        }
        PageResponseDTO<MovieDTO> pageResponseDTO = movieService.getPage(pageRequestDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("pageSize", pageRequestDTO.getPageSize());
        return "/movie/main";
    }

    @GetMapping("/read")
    public String read(Long mid, @Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("mid", mid);
            return "redirect:/movie/read";
        }
        MovieDTO movieDTO = movieService.getOne(mid);
        List<ReviewDTO> dtoList = reviewService.getReview(movieDTO.getTitle());
        model.addAttribute("movieDTO", movieDTO);
        model.addAttribute("reviewDTOList", dtoList);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/movie/read";
    }

    @PostMapping("/reg")
    public String reg(Long mid,
                      @Valid ReviewDTO reviewDTO, BindingResult bindingResult,
                      @Valid PageRequestDTO pageRequestDTO, BindingResult pageBindingResult,
                      RedirectAttributes redirectAttributes,
                      Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("mid", mid);
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/movie/read";
        }
        if (pageBindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", pageBindingResult.getAllErrors());
            redirectAttributes.addAttribute("mid", mid);
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/movie/read";
        }
        reviewService.register(reviewDTO);
        MovieDTO movieDTO = movieService.getOne(mid);
        List<ReviewDTO> dtoList = reviewService.getReview(movieDTO.getTitle());
        model.addAttribute("movieDTO", movieDTO);
        model.addAttribute("reviewDTOList", dtoList);
        redirectAttributes.addAttribute("mid", mid);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/movie/read";
    }

    @PostMapping("/update")
    public String updateLogic(Long mid,
                              @Valid ReviewDTO reviewDTO, BindingResult bindingResult,
                              @Valid PageRequestDTO pageRequestDTO, BindingResult pageBindingResult,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("mid", mid);
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            log.info("업데이트 reviewDTO 유효성 오류");
            return "redirect:/movie/read";
        }
        if (pageBindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", pageBindingResult.getAllErrors());
            redirectAttributes.addAttribute("mid", mid);
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            log.info("업데이트 pageRequestDTO 유효성 오류");
            return "redirect:/movie/read";
        }
        log.info(reviewDTO);
        reviewService.update(reviewDTO);
        MovieDTO movieDTO = movieService.getOne(mid);
        List<ReviewDTO> dtoList = reviewService.getReview(movieDTO.getTitle());
        model.addAttribute("movieDTO", movieDTO);
        model.addAttribute("reviewDTOList", dtoList);
        redirectAttributes.addAttribute("mid", mid);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/movie/read";
    }

    @PostMapping("/delete")
    public String delete(Long mid,
                         Long rid,
                         @Valid PageRequestDTO pageRequestDTO, BindingResult pageBindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model
    ) {
        if (pageBindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", pageBindingResult.getAllErrors());
            redirectAttributes.addAttribute("mid", mid);
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/movie/read";
        }
        reviewService.delete(rid);
        MovieDTO movieDTO = movieService.getOne(mid);
        List<ReviewDTO> dtoList = reviewService.getReview(movieDTO.getTitle());
        model.addAttribute("movieDTO", movieDTO);
        model.addAttribute("reviewDTOList", dtoList);
        redirectAttributes.addAttribute("mid", mid);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/movie/read";
    }
}
