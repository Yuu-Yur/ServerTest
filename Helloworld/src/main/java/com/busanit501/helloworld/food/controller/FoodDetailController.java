package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FoodDetailController", urlPatterns = "/food/detail")
public class FoodDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int fno = Integer.parseInt(request.getParameter("fno"));
        try {
            FoodVO foodVO = new FoodDAO().selectByFno(fno);
            request.setAttribute("foodVO",foodVO);
            request.getRequestDispatcher("/WEB-INF/food/food_detail.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("button").equals("delete")) {
            FoodVO foodVO = FoodVO.builder().fno(Integer.parseInt(request.getParameter("iFno"))).build();
            try {
                new FoodDAO().deleteFood(foodVO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/food/input");
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
