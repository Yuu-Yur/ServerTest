package com.busanit501.bootpractice.controller;

import com.busanit501.bootpractice.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
    //    private final ReplyService replyService;
    // swagger ui 에서 표시될 태그
    @Tag(name = "post 로 댓글 등록", description = "post 형식으로 댓글 등록 진행")
    // /replies/ 로 json 받는 post    
    // 반환 자료형은 ResponseEntity
    // 들어있는 필드는 데이터, http 상태 코드 (200 ok , 404 not found 같은것)
    // 클라이언트 JSON 특정 형태의 데이터 (내용 String 인 map 과 비슷) < - >  서버 일반 클래스 , 여기선 ReplyDTO
    // 클라이언트 -> 서버 @RequestBody 로 역직렬화(특정 형태의 데이터 -> 일반 클래스)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult)
            throws BindException  {
        // 유효성 체크 해서 BindException throws 이걸 CustomRestAdvice 에서 대응
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        log.info("ReplyContoller 에서 받은 JSON -> ReplyDTO = " + replyDTO);
        return ResponseEntity.ok(Map.of("rno", 100L));
    } // 즉 JSON 을 ReplyDTO 로 컨버팅 (RequestBOdy) 해서 받고 안에서 처리를 한 후 ResponseEntity 형태로 반환해줌
}
