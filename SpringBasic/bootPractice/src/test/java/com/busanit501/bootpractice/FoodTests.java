package com.busanit501.bootpractice;

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

import java.util.List;

@SpringBootTest
@Log4j2
public class FoodTests {
    @Autowired
    private FoodRepository foodRepository;

    // 1. 쿼리스트링
    @Test
    public void QSTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<FoodBoard> result = foodRepository.findByNameContainingOrderByFnoDesc("2",pageable);
        log.info(result.getContent());
    }
    @Test
    // 2. @Query annotation
    public void QATest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<FoodBoard> result = foodRepository.findByKeyword("20",pageable);
        log.info(result.getContent());
    }
    @Test
    // 3. Querydsl
    public void QTest() {
        String[] types = {"name","price","shop"};
        String keyword = "10";
        int price = 25;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<FoodBoard> result = foodRepository.search(types,keyword,price,pageable);
        log.info(result.getContent());
    }
}
