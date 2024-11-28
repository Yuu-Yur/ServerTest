package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "FoodDetailController", urlPatterns = "/food/detail")
public class FoodDetailController extends HttpServlet {
    FoodService foodService = FoodService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int fno = Integer.parseInt(request.getParameter("fno"));
            request.setAttribute("foodDTO",foodService.getFoodByFno(fno));
            // 이름이 viewedFoods인 쿠키를 가져와서, 없다면 만들어서라도 가져와서
            Cookie cookie = findCookie(request.getCookies(),"viewedFoods");
            log.info(cookie);
            // value를 가져옴
            String cookieValue = cookie.getValue();
            boolean valueExists = false;
            // 쿠키가 존재하고 넣을 값이 존재한다면
            if (cookieValue != null && cookieValue.indexOf(fno + "-") > 0) {
                valueExists = true;
            } else {
                // 쿠키는 존재하는데 넣을 값이 없다면
                cookieValue += fno + "-";
                cookie.setValue(cookieValue);
                cookie.setMaxAge(60 * 1 * 1);
                cookie.setPath("/");
                // 서버에서 쿠키를 응답으로 클라이언스에 주기
                response.addCookie(cookie);
            }

            request.getRequestDispatcher("/WEB-INF/food/food_detail.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("button").equals("delete")) {
            FoodDTO foodDTO = FoodDTO.builder().fno(Integer.parseInt(request.getParameter("iFno"))).build();
            try {
                foodService.deleteFood(foodDTO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/food/main?button=read");
        } else if (request.getParameter("button").equals("update")) {
            FoodDTO foodDTO = FoodDTO.builder()
                    .fno(Integer.parseInt(request.getParameter("iFno")))
                    .title(request.getParameter("iTitle"))
                    .price(Integer.parseInt(request.getParameter("iPrice")))
                    .counter(Integer.parseInt(request.getParameter("iCounter")))
                    .build();
            try {
                foodService.updateFood(foodDTO);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/food/detail?fno=" + foodDTO.getFno());
        }
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie foundCookie = null;
        log.info(cookies);
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    foundCookie = cookie;
                    break;
                }
            }
        }
        if (foundCookie == null) {
            foundCookie = new Cookie("viewedFoods", "");
            foundCookie.setMaxAge(60 * 1 * 1);
            // 유효 패스 설정, 이것은 모든 경로에서 자동으로 전송
            foundCookie.setPath("/");
            log.info(foundCookie);
        }
        return foundCookie;
    }
}
