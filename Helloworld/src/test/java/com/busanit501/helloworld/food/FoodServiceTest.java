package com.busanit501.helloworld.food;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class FoodServiceTest {
    private FoodService foodService;

    @BeforeEach
    public void ready() {
        foodService = FoodService.INSTANCE;
    }

    @Test
    public void serviceRegisterTest() throws SQLException {
        FoodDTO foodDTO = FoodDTO.builder().title("새 메뉴").price(8000).build();

        foodService.register(foodDTO);
    }

    @Test
    public void serviceReadTest() throws SQLException {
        List<FoodDTO> foodDTOList = foodService.getAllFood();
        for (FoodDTO foodDTO : foodDTOList) {
            log.info(foodDTO);
        }
    }

    @Test
    public void serviceDetailTest() throws SQLException {
        FoodDTO foodDTO = foodService.getFoodByFno(1);
        log.info(foodDTO);
    }

    @Test
    public void serviceUpdateTest() throws SQLException {
        FoodDTO foodDTO = foodService.lastAddedFood();
        log.info("변경 전 : " + foodService.lastAddedFood());
        foodDTO.setPrice(5000);
        foodService.updateFood(foodDTO);
        log.info("변경 후 : " + foodService.lastAddedFood());
    }

    @Test
    public void serviceDeleteTest() throws SQLException {
        FoodDTO foodDTO = foodService.lastAddedFood();
        log.info("삭제 전 : " + foodService.lastAddedFood());
        foodService.deleteFood(foodDTO);
        log.info("삭제 후 : " + foodService.lastAddedFood());
    }
}
