package com.busanit501.bootpractice.food;

import com.busanit501.bootpractice.domain.FoodBoard;
import com.busanit501.bootpractice.domain.FoodReply;
import com.busanit501.bootpractice.dto.FoodReplyDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.repository.FoodReplyRepository;
import com.busanit501.bootpractice.service.FoodReplyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class FoodReplyTest {
    @Autowired
    private FoodReplyRepository foodReplyRepository;
    @Autowired
    private FoodReplyService foodReplyService;


    @Test
    @Transactional
    public void test() {
        IntStream.range(1, 11).forEach(i -> {
            FoodReply fr = FoodReply.builder()
                    .foodBoard(FoodBoard.builder().fno(1L).build())
                    .replyAuthor("더미 작성자" + i)
                    .replyText("더미 댓글" + i)
                    .build();
            foodReplyRepository.save(fr);
            log.info(fr);
        });
    }

    @Test
    public void test2() {
        Long result = foodReplyService.update(FoodReplyDTO.builder()
                .replyId(62L)
                .fno(1L)
                .replyText("testupdate")
                .replyAuthor("tester")
                .build());
        log.info(result);
    }

    @Test
    public void test3() {
        Long result = foodReplyService.register(FoodReplyDTO.builder()
                .fno(1L)
                .replyText("testregister")
                .replyAuthor("tester")
                .build());
        log.info(result);
    }

    @Test
    public void test4() {
        foodReplyService.delete(62L);
    }

    @Test
    public void test5() {
        FoodReplyDTO result = foodReplyService.getFoodReplyById(1L);
        log.info(result);
    }

    @Test
    public void test6() {
        PageResponseDTO result = foodReplyService.getFoodReplyPage(1L, PageRequestDTO.builder().page(1).size(10).build());
        log.info(result);
    }

    @Test
    public void test7() {
        Page<FoodReply> result = foodReplyRepository.listofFoodReply(1L, PageRequest.of(0,10, Sort.by("replyId").descending()));
        log.info(result.getContent());
        log.info(result.getTotalElements());
    }

}
