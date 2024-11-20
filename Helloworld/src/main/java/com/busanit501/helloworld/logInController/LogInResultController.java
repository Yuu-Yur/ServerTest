package com.busanit501.helloworld.logInController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogInResultController", urlPatterns = "/logIn/result")
public class LogInResultController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Log In Result</h1>");
        out.println("<h2>ID: " + email + "</h2>");
        out.println("<h2>Password: " + password + "</h2>");
        out.println("</body></html>");
    }
}
