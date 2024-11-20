package com.busanit501.helloworld.calc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿 = 컨트롤러 역할
// 웹브라우저에서 온 요청에 대해
// 화면,데이터를 전달하는 중간 정류장
@WebServlet(name = "calcInputController", urlPatterns = "/calc/input")
// 웹 브라우저에서, 주소 : ~~~/calc/input 입력하면 이 파일이 응답 -> 입력화면으로 전달
public class CalcInputController extends HttpServlet {
// 화면접근은 get 로직처리 post
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGET 호출");
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/calc/calc_input.jsp");
        rd.forward(req,resp);
    }
}
