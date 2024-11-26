package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.dto.FoodVO;
import com.busanit501.helloworld.food.service.FoodService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

@WebServlet(name = "FoodMainController", urlPatterns = "/food/main")
public class FoodMainController extends HttpServlet {
    FoodService foodService = FoodService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
                RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/food/food_main.jsp");
                rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getParameter("button") == null ? "default" : req.getParameter("button")) {
            case "register":
                try {
                    foodService.register(FoodDTO.builder().title(req.getParameter("iTitle")).price(Integer.parseInt(req.getParameter("iPrice"))).build());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("/food/main?button=read");
        }
    }
}
