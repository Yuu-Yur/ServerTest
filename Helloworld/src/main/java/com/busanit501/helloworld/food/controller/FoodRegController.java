package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FoodRegController", urlPatterns = "/food/register")
public class FoodRegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/food/food_register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("iTitle");
        int price = Integer.parseInt(request.getParameter("iPrice"));
        try {
            new FoodDAO().insertFood(FoodDTO.builder().title(title).price(price).build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/food/read");
    }
}
