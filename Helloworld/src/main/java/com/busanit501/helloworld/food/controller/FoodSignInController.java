package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.jdbcex.DTO.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Log4j2
@WebServlet(name = "FoodSignInController", urlPatterns = "/food/signIn")
public class FoodSignInController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            MemberService memberService = MemberService.INSTANCE;
            HttpSession session = req.getSession();
            MemberDTO user = memberService.signIn(req.getParameter("id"));
            // 정상 로그인이면
            if (user != null && user.getPw().equals(req.getParameter("pw"))) {
                boolean autoSI = req.getParameter("autoSI") != null && req.getParameter("autoSI").equals("on");
                // 자동 로그인 희망이면
                if (autoSI) {
                    //uuid생성, 저장, 다시불러오기
                    String uuid = UUID.randomUUID().toString();
                    memberService.updateUuid(uuid, user.getMno());
                    user = memberService.signIn(req.getParameter("id"));
                    //쿠키 생성, 브라우저에 리스폰스
                    Cookie cookie = new Cookie("autoSI", uuid);
                    cookie.setPath("/");
                    cookie.setMaxAge(60*60*24*7);//7일
                    resp.addCookie(cookie);
                }
                req.getSession().setAttribute("user", user);
                if (session.getAttribute("fromReg") != null && (boolean) session.getAttribute("fromReg")) {
                    session.removeAttribute("fromReg");
                    resp.sendRedirect("/food/main?button=read");
                    return;
                }
                resp.sendRedirect("/food/main");
            } else {
                log.info("아이디,비밀번호가 틀렸습니다.");
                resp.sendRedirect("/food/main?button=signIn");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/food/food_signIn.jsp").forward(req, resp);
    }
}
