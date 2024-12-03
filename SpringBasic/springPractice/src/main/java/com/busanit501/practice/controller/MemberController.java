package com.busanit501.practice.controller;

import com.busanit501.practice.controller.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

    // void return 이면 method name 에 prefix suffix 처리한 view 로 가는 설정
    // 쿼리스트링으로 서버가 받은 데이터를 받음
    // 일반형도, 객체형도 아니므로 Model 에 담아줌
    @GetMapping("/list")
    public void list(String mem, Model model) {
        log.info(mem);
        model.addAttribute("mem", mem);
    }

    // 기본적으로 서버에서 받을땐 String 으로 받음
    // 기본, 객체 자료형이 아닌 경우 형변환이 필요함
    // parse, valueOf 해주는 Formatter 필요
    @GetMapping("/date")
    public String date(String date, RedirectAttributes redirectAttributes) {
        log.info("from date" + date);
        // flash 는 일회용이지만 일반적인 형태로 가지는 않는다
        redirectAttributes.addFlashAttribute("id", "testid");
        redirectAttributes.addFlashAttribute("pw", "testpw");
        redirectAttributes.addFlashAttribute("regdate", date);
        // date 는 formatter 를 거침 redirect 에 넣어서 보내면 get 과 유사하게 감
        redirectAttributes.addAttribute("id","testid");
        redirectAttributes.addAttribute("pw","testpw");
        redirectAttributes.addAttribute("regdate",date);
        return "redirect:/member/showDate";
    }
    // 여기서 받는데 String, date 로 받지 않고 한번에 DTO로 받을 수 있다
    @GetMapping("/showDate")
    public void showDate(MemberDTO member) {
        log.info("from showDate" + member);
    }
}
