package com.busanit501.springbackend.controller;

import com.busanit501.springbackend.dto.MovieDTO;
import com.busanit501.springbackend.dto.PageRequestDTO;
import com.busanit501.springbackend.dto.PageResponseDTO;
import com.busanit501.springbackend.service.MovieService;
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

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/movie")
public class MainPageController {
    private final MovieService movieService;

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

    @RequestMapping("/read")
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
        model.addAttribute("movieDTO", movieDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "/movie/read";
    }

    @GetMapping("/update")
    public String update(Long mid, @Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        log.info("TodoController update :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("mid", mid);
            return "redirect:/movie/update";
        }
        MovieDTO movieDTO = movieService.getOne(mid);
        log.info("TodoController update 데이터 유무 확인 :" + movieDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("movieDTO", movieDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/movie/update";

    }

    @PostMapping("/update")
    public String updateLogic(@Valid MovieDTO movieDTO, BindingResult bindingResult,
                              @Valid PageRequestDTO pageRequestDTO,
                              BindingResult pageBindingResult,
                              RedirectAttributes redirectAttributes) {

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :TodoDTO ");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            //redirectAttributes 이용해서, 쿼리 스트링으로 전달.
            redirectAttributes.addAttribute("mid", movieDTO.getMid());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/movie/update"+pageRequestDTO.getLink();
        }

        if (pageBindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :PageRequestDTO ");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors2", pageBindingResult.getAllErrors());
            //redirectAttributes 이용해서, 쿼리 스트링으로 전달.
            redirectAttributes.addAttribute("mid", movieDTO.getMid());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/movie/update"+pageRequestDTO.getLink();
        }

        // 수정하는 로직 필요함.
        // 주의사항, 체크박스의 값의 문자열 on 전달 받습니다.
        log.info("todoDTO확인 finished의 변환 여부 확인2. : " + movieDTO);
        log.info("TodoController update pageRequestDTO : "+ pageRequestDTO);

        movieService.update(movieDTO);
        // 쿼리 스트링으로 , 목록에 전달함.
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/movie/main?"+pageRequestDTO.getLink();
    }
    @PostMapping("/delete")
    public String delete(Long mid, PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes
    ) {
        movieService.delete(mid);
        log.info("TodoController delete : pageRequestDTO " + pageRequestDTO);
        return "redirect:/movie/main?"+pageRequestDTO.getLink();
    }
}
