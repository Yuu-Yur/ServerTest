package com.busanit501.bootpractice.board;

import com.busanit501.bootpractice.domain.Board;
import com.busanit501.bootpractice.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void insertTest(){
        IntStream.range(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("샘플" + i)
                    .content("내용" + i)
                    .author("작성자" + i)
                    .build();
           // crud -> 1차 임시 테이블 -> 실제 테이블
            // save -> insert or update
            // boardRepository 는 비어있지만 jpaRepository 를 상속
            // 기본 method 중 save 사용
            Board result = boardRepository.save(board);
            log.info(result);
        });
    }
    @Test
    public void updateTest(){
        Long bno = 99L;
        Optional<Board> result = boardRepository.findById(bno);
        // 임시 테이블 (엔티티)
        Board board = result.orElseThrow();
        board.changeTC("마지막 제목", "마지막 내용");
        // 임시 테이블 -> 실제 테이블
        boardRepository.save(board);
    }
    @Test
    public void deleteTest(){
        Long bno = 98L;
        boardRepository.deleteById(bno);
        bno = 97L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.changeTC("마지막 제목","마지막 내용");
        boardRepository.save(board);
    }
    @Test
    public void findSelectOneTest(){
        Long bno = 99L;
        // Optional 은 있으면 해당 인스턴스 가져오기 없으면 null
        Optional<Board> result = boardRepository.findById(bno);
        // 있으면 받고 없으면 예외 throw
        Board board = result.orElseThrow();
        log.info("하나 조회 : " + board);
    }

    @Test
    public void findAllTest(){
        List<Board> result = boardRepository.findAll();
        for (Board board : result) {
            log.info(board);
        }
    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        log.info(result.getTotalElements() + " : gTE 전체 갯수");
        log.info(result.getTotalPages() + " : gTP 전체 페이지 수");
        log.info(result.getContent() + " : gC 페이징 된 내용");
        log.info(result.getNumber() + " : gN 페이징 된 번호");
        log.info(result.getSize() + " : gS 크기");
    }

    @Test
    public void testQuery(){
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findByKeyword("샘",pageable);
        log.info("페이징 결과물 : " + result.getContent().get(0));
    }

    @Test
    public void testQuerydsl(){
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        boardRepository.search(pageable);
    }

    @Test
    public void testQuerydsl2(){
        String keword = "3";
        String[] types = {"t", "a", "c"};
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types,keword,pageable);
        log.info(" gTE 전체 갯수 : " + result.getTotalElements());
        log.info(" gTP 전체 페이지 수 : " + result.getTotalPages());
        log.info(" gC 페이징 된 내용 : " + result.getContent());
        log.info(" gN 페이징 된 번호 : " + result.getNumber());
        log.info(" gS 크기 : " + result.getSize());
        log.info(" hN 다음? : " + result.hasNext());
        log.info(" hP 이전? : " + result.hasPrevious());

    }
}
