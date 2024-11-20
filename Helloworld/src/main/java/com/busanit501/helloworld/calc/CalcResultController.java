package com.busanit501.helloworld.calc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 입력화면 접근은, jsp 파일에 직접 접근하지 않고
// calc/input 으로 서블릿에 접근
// 서블릿(자바)이 뷰파일(jsp) 전달
@WebServlet(name = "calcResultController", urlPatterns = "/calc/result")
public class CalcResultController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        System.out.println(num1 + " " + num2);
//                                    WEB-INF 특수한 폴더, 직접 접근 못하는 보안설정
        response.sendRedirect("/WEB-INF/calc/calc_result.jsp");
    }
}
