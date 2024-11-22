package com.busanit501.helloworld.food;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class FoodDAOTest {
    @Test
    public void foodInsertTest() throws SQLException {
        FoodDTO foodDTO = FoodDTO.builder().title("새 메뉴").price(20000).build();
        FoodDAO foodDAO = new FoodDAO();
        foodDAO.insertFood(foodDTO);
    }

    @Test
    public void foodReadTest() throws SQLException {
        List<FoodDTO> foodList;
        FoodDTO foodDTO;
        FoodDAO foodDAO = new FoodDAO();
        foodList = foodDAO.readFood();
        for (FoodDTO foodDTO1 : foodList) {
            System.out.println(foodDTO1);
        }
    }

    @Test
    public void foodUpdateTest() throws SQLException {
        FoodVO foodVO = FoodVO.builder().fno(6).title("수정한 메뉴이름").price(6000).counter(3).build();
        FoodDAO foodDAO = new FoodDAO();
        foodDAO.updateFood(foodVO);
    }

    @Test
    public void foodDeleteTest() throws SQLException {
        FoodVO foodVO = FoodVO.builder().fno(7).build();
        FoodDAO foodDAO = new FoodDAO();
        foodDAO.deleteFood(foodVO);
    }
}