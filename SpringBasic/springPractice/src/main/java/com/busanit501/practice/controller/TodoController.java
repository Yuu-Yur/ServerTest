package com.busanit501.practice.controller;

import com.busanit501.practice.controller.dto.TodoDTO;
import com.busanit501.practice.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @RequestMapping("/list")
    public void list(Model model) {
        log.info("void return 이므로 /WEB-INF/views/todo/list.jsp");
        List<TodoDTO> list = todoService.getAll();
        model.addAttribute("list", list);
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public void reg() {
        log.info("reg 화면 호출");
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String regP(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("reg post DTO 받아오기" + todoDTO);
        // 유효성 체크에 걸리는 내용은 BindingResult 에 저장
        // 즉 아래 코드는 유효성 체크가 걸리는 것이 있다면
        if (bindingResult.hasErrors()) {
            log.info("유효성 에러 발생");
            // 오류사항을 담아서 redirect
            redirectAttributes.addFlashAttribute("errors", bindingResult);
            log.info(bindingResult.getAllErrors());
            log.info(bindingResult.getFieldError().getDefaultMessage());
            return "redirect:/todo/reg";
        }
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    @RequestMapping("/read")
    public void read(Long tno, Model model) {
        log.info(tno);
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info("TodoController read 데이터 유무 확인 :" + todoDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("todoDTO", todoDTO);
    }

    @RequestMapping("/update")
    public void update(Long tno, Model model) {
        log.info("TodoController update :");
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info("TodoController update 데이터 유무 확인 :" + todoDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("todoDTO", todoDTO);
    }

    @RequestMapping("/delete")
    public String delete(Long tno) {
        log.info("C 에서" + tno + "delete");
        todoService.delete(tno);
        return "redirect:/todo/list";
    }
}
