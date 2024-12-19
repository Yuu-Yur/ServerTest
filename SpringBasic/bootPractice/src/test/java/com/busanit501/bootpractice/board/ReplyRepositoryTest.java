package com.busanit501.bootpractice.board;

import com.busanit501.bootpractice.domain.Board;
import com.busanit501.bootpractice.domain.Reply;
import com.busanit501.bootpractice.dto.BoardListReplyCountDTO;
import com.busanit501.bootpractice.repository.BoardRepository;
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
    @Autowired
    private BoardRepository boardRepository;

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

    @Test
    public void testSelectWithReplyCount() {
        Pageable pageable= PageRequest.of(0,10, Sort.by("rno").descending());
        String keyword = "오늘";
        String[] types = {"t","w","c"};
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);
    }
}
