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
import java.sql.Date;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "MemberMyPageController", urlPatterns = "/member/main/myPage")
public class MemberMyPageController extends HttpServlet {
    MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            if (Integer.parseInt(req.getParameter("mno")) == (int) session.getAttribute("signInInfo")) {
                req.setAttribute("dto", memberService.getMyInfo(Integer.parseInt(req.getParameter("mno"))));
                req.getRequestDispatcher("/WEB-INF/jdbcex/member_myPage.jsp").forward(req, resp);
            } else {
                // 다른 사용자의 정보에 접근하려 하면
                resp.sendRedirect("/member/main/signOut");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("button") == null ? "default" : req.getParameter("button")) {
            case "update":
                try {
                    memberService.edit(MemberDTO.builder()
                            .mno(Integer.parseInt(req.getParameter("mno")))
                            .id(req.getParameter("id"))
                            .pw(req.getParameter("pw"))
                            .regdate(Date.valueOf(req.getParameter("regdate")))
                            .build());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/member/main/myPage?mno=" + req.getParameter("mno"));
                break;
            case "delete":
                try {
                    memberService.delete(MemberDTO.builder().mno(Integer.parseInt(req.getParameter("mno"))).build());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/member/main");
                break;
            case "default":
                resp.sendRedirect("/member/main/signOut");
        }
    }
}
