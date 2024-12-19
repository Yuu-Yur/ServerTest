package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.domain.Reply;
import com.busanit501.bootpractice.dto.ReplyDTO;
import com.busanit501.bootpractice.repository.BoardRepository;
import com.busanit501.bootpractice.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        // replyDTO 엔 bno reply 엔 board mapper 가 변환해주지 않음
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        reply.bnoToBoard(boardRepository.findById(replyDTO.getBno()).orElseThrow());
        Reply result = replyRepository.save(reply);
        return result.getRno();
    }
}
