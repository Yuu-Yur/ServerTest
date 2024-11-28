package com.busanit501.helloworld.jdbcex.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "MemberSignOutController", urlPatterns = "/member/main/signOut")
public class MemberSignOutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("signOut 하고 main으로 돌아감");
        HttpSession session = req.getSession();
        session.removeAttribute("signInInfo");
        resp.sendRedirect("/member/main");
    }
}
