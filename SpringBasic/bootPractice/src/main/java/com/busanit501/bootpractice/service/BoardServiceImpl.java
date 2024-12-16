package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.domain.Board;
import com.busanit501.bootpractice.dto.BoardDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;


    @Override
    public void update(BoardDTO boardDTO) {
        boardRepository.save(modelMapper.map(boardDTO, Board.class));
    }

    @Override
    public void delete(Long bno) {
        boardRepository.deleteById(bno);
    }

    @Override
    public void register(BoardDTO boardDTO) {
        boardRepository.save(modelMapper.map(boardDTO, Board.class));
    }

    @Override
    public BoardDTO getBoardById(Long bno) {
        Optional<BoardDTO> result = boardRepository.findById(bno).map(board -> modelMapper.map(board, BoardDTO.class));
        return result.orElse(null);
    }

    @Override
    public Page<BoardDTO> getPage(PageRequestDTO pageRequestDTO) {
        return boardRepository
                .searchAll(pageRequestDTO.getTypes(), pageRequestDTO.getKeyword(), pageRequestDTO.getPageable())
                .map(board -> modelMapper.map(board, BoardDTO.class));
    }
}
