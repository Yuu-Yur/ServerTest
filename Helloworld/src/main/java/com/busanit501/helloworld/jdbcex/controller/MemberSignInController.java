package com.busanit501.helloworld.jdbcex.controller;

import com.busanit501.helloworld.jdbcex.DTO.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "MemberSignInController", urlPatterns = "/member/signIn")
public class MemberSignInController extends HttpServlet {
    MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mno",req.getParameter("mno"));
        req.getRequestDispatcher("/WEB-INF/jdbcex/member_signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int signInInfo;
        HttpSession session = req.getSession();
        String mno = String.valueOf(session.getAttribute("mno"));
        session.removeAttribute("mno");
        String pw = req.getParameter("pw");
        try {
            log.info(mno + pw);
            MemberDTO memberDTO = memberService.getMyInfo(Integer.parseInt(mno));
            if(memberDTO.getPw().equals(pw)) {
                session.setAttribute("signInInfo",memberDTO);
                resp.sendRedirect("/member/main/myPage?mno="+mno);
            } else {
                log.info("로그인 실패");
                session.setAttribute("signInInfo",null);
                resp.sendRedirect("/member/main");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
