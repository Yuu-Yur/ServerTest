package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dao.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.food.service.FoodService;
import com.busanit501.helloworld.jdbcex.DTO.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@Log4j2
@WebServlet(name = "FoodMainController", urlPatterns = "/food/main")
public class FoodMainController extends HttpServlet {
    FoodService foodService = FoodService.INSTANCE;
    MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        log.info(session.getAttribute("user"));
        switch (req.getParameter("button") == null ? "default" : req.getParameter("button")) {
            case "read":
                try {
                    List<FoodDTO> foodList = foodService.getAllFood();
                    req.setAttribute("foodList", foodList);
                    req.getRequestDispatcher("/WEB-INF/food/food_list.jsp").forward(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "favorite":
                try {
                    int fno = new FoodDAO().getFavorite();
                    req.getRequestDispatcher("/food/detail?fno=" + fno).forward(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "random":
                try {
                    List<FoodVO> foodVOList = new FoodDAO().readFoodVO();
                    Random rand = new Random();
                    int fno = foodVOList.get(rand.nextInt(foodVOList.size())).getFno();
                    req.getRequestDispatcher("/food/detail?fno=" + fno).forward(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "signUp":
                req.getRequestDispatcher("/WEB-INF/food/food_signUp.jsp").forward(req, resp);
                break;

            case "signOut":
                session.setAttribute("user",null);
                resp.sendRedirect("/food/main");
                break;

            default:
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/food/food_main.jsp");
                rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        switch (req.getParameter("button") == null ? "default" : req.getParameter("button")) {
            case "signUp":
                try {
                    if (memberService.signIn(req.getParameter("id")) == null) {
                        memberService.register(MemberDTO.builder().id(req.getParameter("id")).pw(req.getParameter("pw")).build());
                        resp.sendRedirect("/food/main?button=signIn");
                    } else {
                        log.info("중복된 아이디입니다.");
                        resp.sendRedirect("/food/main");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
