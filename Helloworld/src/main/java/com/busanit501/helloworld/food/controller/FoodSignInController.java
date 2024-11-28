package com.busanit501.helloworld.food.controller;

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
@WebServlet(name = "FoodSignInController", urlPatterns = "/food/signIn")
public class FoodSignInController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            MemberDTO user = MemberService.INSTANCE.signIn(req.getParameter("id"));
            if (user != null) {
                req.getSession().setAttribute("user", user);
                if ((boolean)session.getAttribute("fromReg")){
                    session.removeAttribute("fromReg");
                    resp.sendRedirect("/food/main?button=read");
                    return;
                }
                resp.sendRedirect("/food/main");
            } else {
                log.info("해당 아이디는 없음");
                resp.sendRedirect("/food/main?button=signIn");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
