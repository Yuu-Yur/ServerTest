package com.busanit501.bootpractice.service;


import com.busanit501.bootpractice.dto.BoardDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardService {
    public void update(BoardDTO boardDTO);
    public void delete(Long bno);
    public void register(BoardDTO boardDTO);
    public BoardDTO getBoardById(Long bno);
    public Page<BoardDTO> getPage(PageRequestDTO pageRequestDTO);
}
