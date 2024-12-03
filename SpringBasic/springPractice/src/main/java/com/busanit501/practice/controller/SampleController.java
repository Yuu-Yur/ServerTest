package com.busanit501.practice.controller;

import com.busanit501.practice.controller.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Log4j2
@Controller
public class SampleController {
    // 1.화면제공(Get)
    // servlet-context 의 prefix, suffix 가 붙어 경로가 됨
    // return 이 없으므로 method 이름 hello 에 prefix, suffix 붙임
    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }

    // 만약 문자열을 return 한다면 해당 문자열에 prefix suffix 가 붙어 경로가 됨
    @GetMapping("/hello2")
    public String hello2() {
        log.info("hello2");
        return "helloTest";
    }
    // void return -> prefix methodName suffix -> viewFileName
    // String return -> prefix returnString suffix -> viewFileName

    @GetMapping("/ex1")
    //파라미터 수집 여부 확인 테스트용
    //void return 이므로 ex1 get, 쿼리스트링 지정 ?name=name&age=20
    //쿼리스트링의 정보를 parameter 매개변수로 받을 수 있음
    public void ex1(String name, int age) {
        log.info(name);
        log.info(age);
    }

    @GetMapping("/ex2")
    // 각각의 parameter 앞에 parameter 이름과 기본값을 설정할 수 있음
    public void ex2(@RequestParam(name = "name", defaultValue = "defaultName") String name,
                    @RequestParam(name = "age", defaultValue = "20") Integer age) {
        log.info(name);
        log.info(age);
    }

    @GetMapping("/ex3")
    // 브라우저에서 넘어온 데이터 타입은 String
    // 자동 형변환되는 기본자료형이 아니면 타입 불일치 오류 400번
    // 자료형이 다른것들끼리 미리 변환할 수 있는 method 만들어둔다
    // formatter class 만들고 servlet context 에서 위치 등록하고
    // annotation 으로 사용 가능하게 driven 해주면 자동으로 형변환을 한다
    public void ex3(LocalDate dueDate) {
        log.info(dueDate);
    }

    // 앞에 예제들은 , 웹 -> 서버, 전달해서, 서버에서 확인.
    // 방향이 반대. 서버 -> 웹 전달, 화면에 데이터 탑재 전달.
    @GetMapping("/ex4")
    public void ex4(Model model) {
        log.info("ex4 Model 서버에서 -> 데이터 전달하기. :");
        model.addAttribute("msg"," <script>\n" + "alert('이것은 JavaScript alert 테스트입니다!, 만약, 공격자가 악성 코드를 이런식으로 문자열에 포함하면 안 좋은일이 생김');\n" + "</script>");
    }

    @GetMapping("/ex5")
    // 웹브라우저에서 TodoDTO 멤버 타입 형식으로 받고,
    // 다시 서버 -> 웹브라우저로 전달 하는 방법.
    // 파라미터 , TodoDTO todoDTO 선언되어있으면,
    // 화면에서 , 그대로 사용가능., 예시) ${todoDTO}
    // localhost:8080/ex5?title=lsy&writer=이상용
    // 모델 Model model 사용안해도, 스프링 프레임워크에서 자동으로 화면에서 사용가능.
    // 즉 매개변수에 지정되어있는 객체를 사용할 것이라면 model.add 안써도 된다는 뜻
    public void ex5(TodoDTO todoDTO) {
        log.info("ex5  :");
        log.info("ex5 : " + todoDTO);
    }

    //리다이렉트 시, 데이터 전달
    // 1) 키, 값의 형태로 데이터 전달,
    // 2) 일회용으로 데이터 전달 예시,
    @GetMapping("/ex6")
    public String ex6(RedirectAttributes redirectAttributes) {
        // 키, 값의 구조로 데이터 전달.
        // 서버 -> 웹 브라우저로 전달,  화면에 데이터를 탑재해서 전달.
        // redirect 보내는 것은 get 즉 쿼리스트링으로 보내는것
        redirectAttributes.addAttribute("msg", "message1");
        // 이것은 일회용 굳이 redirect 목표가 받을 필요가 없음
        redirectAttributes.addFlashAttribute("msg2", "message2");

        // redirect ex7 로
        return "redirect:/ex7";
    }

    @GetMapping("/ex7")
    public void ex7(String msg, Model model) {
        // String 은 일반 자료형도, 객체 자료형도 아니기 때문에 모델에 추가해야함 
        model.addAttribute("msg", msg);
    }

    // 타입 불일치로 강제로 예외 발생 하는 예시
    @GetMapping("/ex8")
    public void ex8(String name, int age) {
        log.info("ex8 name :" + name);
        log.info("ex8 age :" + age);

    }
}
