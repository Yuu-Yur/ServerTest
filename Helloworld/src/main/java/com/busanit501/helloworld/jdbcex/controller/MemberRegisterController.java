package com.busanit501.helloworld.jdbcex.controller;

import com.busanit501.helloworld.jdbcex.DTO.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MemberRegisterController", urlPatterns = "/member/reg")
public class MemberRegisterController extends HttpServlet {
    MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jdbcex/member_reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         MemberDTO memberDTO = MemberDTO.builder()
                 .id(req.getParameter("id"))
                 .pw(req.getParameter("pw"))
                 .build();
        try {
            memberService.register(memberDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/member/main");
    }
}
