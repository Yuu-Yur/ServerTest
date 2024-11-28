package com.busanit501.helloworld.food;

import com.busanit501.helloworld.food.dao.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Log4j2
public class FoodDAOTest {
    @Test
    public void foodInsertTest() throws SQLException {
        FoodVO foodVO = FoodVO.builder().title("새 메뉴").price(20000).build();
        FoodDAO foodDAO = new FoodDAO();
        foodDAO.insertFood(foodVO);
    }

    @Test
    public void selectByFnoTest() throws SQLException {
//        System.out.println(new FoodDAO().selectByFno(2));
        log.info(new FoodDAO().selectByFno(2));
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