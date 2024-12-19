package com.busanit501.bootpractice.food;

import com.busanit501.bootpractice.domain.FoodBoard;
import com.busanit501.bootpractice.repository.FoodRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class FoodRepositoryTests {
    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void insertFood() {
        IntStream.range(0, 500).forEach(i -> {
            boolean revisit = false;
            if (i % 2 == 0) {revisit = true;}
            FoodBoard board = FoodBoard.builder().name("음식 이름" + i).shop("식당 이름" + i).price(i)
                    .revisit(revisit)
                    .build();
            foodRepository.save(board);
        });
    }

    @Test
    public void updateFood() {
        Optional<FoodBoard> result = foodRepository.findById(49L);
        FoodBoard food = result.orElseThrow();
        food.changeNSPR("마지막 음식"
                , "마지막 가게"
                , 9999,
                true);
        log.info(food.toString() + "bookmark");
        foodRepository.save(food);
    }

    @Test
    public void deleteFood() {
        foodRepository.deleteById(50L);

    }

    @Test
    public void findAll() {
        log.info("bookmark");
        log.info(foodRepository.findAll());
    }

    @Test
    public void findOne() {
        Optional<FoodBoard> food = foodRepository.findById(49L);
        log.info("bookmark");
        log.info(food);
    }


    @Test
    public void testPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<FoodBoard> page = foodRepository.findAll(pageable);
        log.info(page.getContent());
    }
}
