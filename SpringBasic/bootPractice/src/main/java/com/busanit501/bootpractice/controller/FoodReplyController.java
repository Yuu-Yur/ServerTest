package com.busanit501.bootpractice.controller;

import com.busanit501.bootpractice.dto.FoodReplyDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.service.FoodReplyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/food/rep")
@Log4j2
@RequiredArgsConstructor
public class FoodReplyController {
    private final FoodReplyService foodReplyService;

    // front(js) -> restController 로 역직렬화 할땐 기본자료형은 바로 보내고 클래스는 Json 형태로 바꿔 보냈지만
    // restController -> front(js) 로 직렬화 할땐 기본 자료형은 Map<String, 보내려는 자료형>, 클래스는 바로 보낼 수 있다.
    @Tag(name = "목록 로드", description = "fno 와 pageRequestDTO(실제론 page 와 size) 와 get 요청을 받아 페이징 된 FoodReplyDTO 반환")
    @GetMapping("/{fno}")
    public PageResponseDTO<FoodReplyDTO> loadReplyPage(@PathVariable Long fno, PageRequestDTO pageRequestDTO){
        PageResponseDTO<FoodReplyDTO> result = foodReplyService.getFoodReplyPage(fno, pageRequestDTO);
        return result;
    }

    @Tag(name = "추가", description = "FoodReplyDTO 를 Post 요청으로 받아서 insert 그 id 를 반환")
    @PostMapping("/reg")
    public Map<String,Long> addReply(@Valid @RequestBody FoodReplyDTO foodReplyDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return Map.of("replyId", foodReplyService.register(foodReplyDTO));
    }

    @Tag(name = "수정", description = "FoodReplyDTO 를 Put 요청으로 받아서 update 하고 Id 를 반환")
    @PutMapping("/upd")
    public Map<String,Long> updateReply(@Valid @RequestBody FoodReplyDTO foodReplyDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return Map.of("replyId", foodReplyService.update(foodReplyDTO));
    }

    @Tag(name = "삭제", description = "ReplyId 를 받아서 삭제 후 그 ReplyId 를 반환")
    @DeleteMapping("/del/{replyId}")
    public Map<String,Long> delReply(@PathVariable Long replyId) {
        foodReplyService.delete(replyId);
        return Map.of("replyId", replyId);
    }

    @Tag(name = "세부 조회", description = "모달에서 쓸 댓글의 세부사항 조회")
    @GetMapping("/detail/{replyId}")
    public FoodReplyDTO loadReplyDetail(@PathVariable Long replyId) {
        return foodReplyService.getFoodReplyById(replyId);
    }
}
