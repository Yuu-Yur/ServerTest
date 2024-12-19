package com.busanit501.bootpractice;

import com.busanit501.bootpractice.dto.BoardDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class BoardServiceTest {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void test() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(1L)
                .title("update 테스트")
                .author("테스트")
                .content("update 테스트 중")
                .build();
        boardService.update(boardDTO);
    }
    @Test
    public void test2() {
        Long boardId = 1L;
        boardService.delete(boardId);
    }
    @Test
    public void test3() {
        Long boardId = 1L;
        BoardDTO boardDTO = boardService.getBoardById(boardId);
        log.info(boardDTO);
    }
    @Test
    public void test4() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("register 테스트")
                .author("테스트")
                .content("register 테스트 중")
                .build();
        boardService.register(boardDTO);
    }
    @Test
    public void test5() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("3")
                .build();
        Page<BoardDTO> result = boardService.getPage(pageRequestDTO);
        log.info(result.getContent());
        log.info(result.getTotalPages());
        log.info(result.hasNext());
        log.info(result.hasPrevious());
        log.info(result.isFirst());
    }
}
