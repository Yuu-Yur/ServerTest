package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FoodRegController", urlPatterns = "/food/reg")
public class FoodRegController extends HttpServlet {
    FoodService foodService = FoodService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/food/food_register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            foodService.register(FoodDTO.builder().title(req.getParameter("iTitle")).price(Integer.parseInt(req.getParameter("iPrice"))).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/food/main?button=read");
    }
}
