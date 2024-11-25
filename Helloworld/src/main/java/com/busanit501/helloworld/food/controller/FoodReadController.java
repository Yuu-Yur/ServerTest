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
import java.util.List;
import java.util.Random;

@WebServlet(name = "foodReadController", urlPatterns = "/food/read")
public class FoodReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("button")) {
            case "null":
            case "read":
                List<FoodVO> foodList;
                FoodDAO foodDAO = new FoodDAO();
                try {
                    foodList = foodDAO.readFoodVO();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                req.setAttribute("foodList", foodList);
                req.getRequestDispatcher("/WEB-INF/food/food_read.jsp").forward(req, resp);
                break;

            case "register":
                req.getRequestDispatcher("/WEB-INF/food/food_register.jsp").forward(req, resp);
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
            default:
                    resp.sendRedirect("/food/input");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FoodVO> foodList;
        int wPrice = Integer.parseInt(req.getParameter("wPrice"));
        FoodDAO foodDAO = new FoodDAO();
        try {
            foodList = foodDAO.selectByPrice(wPrice);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        StringBuilder SB = new StringBuilder("<ul>");
        for (FoodVO food : foodList) {
            SB.append("<li>")
                    .append("<a href='/food/detail?fno=")
                    .append(food.getFno())
                    .append("'>")
                    .append(food)
                    .append("</a>")
                    .append("</li>");
        }
        SB.append("</ul>");
        req.setAttribute("sb", SB);
        req.getRequestDispatcher("/WEB-INF/food/food_read2.jsp").forward(req, resp);
    }
}
