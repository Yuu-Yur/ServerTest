package com.busanit501.helloworld;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "myServlet" , urlPatterns = "/my")
public class MyServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        // 응답, 서버 to 웹브라우저 응답. 웹 브라우저, text/html
        resp.setContentType("text/html");
        
        // 출력 인스턴스로 웹브라우저에게 응답
        PrintWriter out = resp.getWriter();
        
        // 응답할 내용을 작성, 받는 웹브라우저는 html을 사용
        out.println("<html><body>");
        out.println("<h1>Hello World 하청빈</h1>");
        out.println("</body></html>");
    }
        
}
