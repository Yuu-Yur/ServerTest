package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.food.service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FoodDetailController", urlPatterns = "/food/detail")
public class FoodDetailController extends HttpServlet {
    FoodService foodService = FoodService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("foodDTO",foodService.getFoodByFno(Integer.parseInt(request.getParameter("fno"))));
            request.getRequestDispatcher("/WEB-INF/food/food_detail.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("button").equals("delete")) {
            FoodVO foodVO = FoodVO.builder().fno(Integer.parseInt(request.getParameter("iFno"))).build();
            try {
                new FoodDAO().deleteFood(foodVO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/food/main?button=read");
        } else {
            FoodVO foodVO = FoodVO.builder()
                    .fno(Integer.parseInt(request.getParameter("iFno")))
                    .title(request.getParameter("iTitle"))
                    .price(Integer.parseInt(request.getParameter("iPrice")))
                    .counter(Integer.parseInt(request.getParameter("iCounter")))
                    .build();
            try {
                new FoodDAO().updateFood(foodVO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/food/detail?fno=" + foodVO.getFno());
        }
    }
}
