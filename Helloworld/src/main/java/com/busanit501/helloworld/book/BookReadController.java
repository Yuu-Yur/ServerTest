package com.busanit501.helloworld.book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookReadController", urlPatterns = "/book/read")
public class BookReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        BookDTO book = BookService.INTERFACE.getBook(bookId);
        request.setAttribute("book", book);
        request.getRequestDispatcher("/WEB-INF/book/read.jsp").forward(request,response);
    }
}
