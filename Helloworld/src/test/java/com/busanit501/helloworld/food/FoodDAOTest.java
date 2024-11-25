package com.busanit501.helloworld.food;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.dto.FoodVO;
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
        FoodDAO foodDAO = new FoodDAO();
        foodList = foodDAO.readFood();
        for (FoodDTO foodDTO1 : foodList) {
            System.out.println(foodDTO1);
        }
//        foodList.forEach(System.out::println); 위의 foreach 와 같음
    }

    @Test
    public void selectByFnoTest() throws SQLException {
        System.out.println(new FoodDAO().selectByFno(2));
    }

    @Test
    public void foodUpdateTest() throws SQLException {
        FoodVO foodVO = FoodVO.builder().fno(10).title("수정한 메뉴이름").price(6000).counter(3).build();
        FoodDAO foodDAO = new FoodDAO();
        foodDAO.updateFood(foodVO);
    }

    @Test
    public void foodDeleteTest() throws SQLException {
        FoodVO foodVO = FoodVO.builder().fno(10).build();
        FoodDAO foodDAO = new FoodDAO();
        foodDAO.deleteFood(foodVO);
    }
}