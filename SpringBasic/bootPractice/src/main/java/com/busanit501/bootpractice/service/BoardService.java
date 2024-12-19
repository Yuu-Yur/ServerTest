package com.busanit501.bootpractice.service;


import com.busanit501.bootpractice.dto.BoardDTO;
import com.busanit501.bootpractice.dto.BoardListReplyCountDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import org.springframework.data.domain.Page;


public interface BoardService {
    public void update(BoardDTO boardDTO);
    public void delete(Long bno);
    public void register(BoardDTO boardDTO);
    public BoardDTO getBoardById(Long bno);
    public Page<BoardDTO> getPage(PageRequestDTO pageRequestDTO);
    public PageResponseDTO<BoardListReplyCountDTO> getPageWithReplyCount(PageRequestDTO pageRequestDTO);

}
