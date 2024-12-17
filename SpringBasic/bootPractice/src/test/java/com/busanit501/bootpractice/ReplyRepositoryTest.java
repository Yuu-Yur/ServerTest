package com.busanit501.bootpractice;

import com.busanit501.bootpractice.domain.Board;
import com.busanit501.bootpractice.domain.Reply;
import com.busanit501.bootpractice.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert() {
        Board board = Board.builder().bno(2L).build();
        Reply reply = Reply.builder()
                .board(board)
                .replyText("테스트 댓글")
                .replyer("테스트 댓글 작성자")
                .build();
        replyRepository.save(reply);
    }

    @Test
    public void testSelect() {
        Pageable pageable= PageRequest.of(0,10, Sort.by("rno").descending());
        Page<Reply> result = replyRepository.listOfBoard(2L,pageable);
//        PageResponseDTO<Reply>
        log.info(result.getContent());
    }
}
