package com.busanit501.helloworld.book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookDeleteController", urlPatterns = "/book/delete")
public class BookDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dBookName = request.getParameter("dBookName");
        System.out.println("삭제할 책 제목은 : " + dBookName);
        response.sendRedirect("/book/list");
    }
}
