package com.busanit501.helloworld.signInController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignInResultController", urlPatterns = "/signIn/result")
public class SignInResultController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ID = request.getParameter("ID");
        String Password = request.getParameter("Password");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Sign In Result</h1>");
        out.println("<h2>ID: " + ID + "</h2>");
        out.println("<h2>Password: " + Password + "</h2>");
        out.println("</body></html>");
    }
}
