package com.busanit501.bootpractice.board;

import com.busanit501.bootpractice.dto.ReplyDTO;
import com.busanit501.bootpractice.service.ReplyService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {
    @Autowired
    private ReplyService replyService;

    @Test
    @Transactional
    public void testRegister() {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("test")
                .replyer("tester")
                .bno(2L)
                .build();
        Long result = replyService.register(replyDTO);
        log.info(result);
    }
}
