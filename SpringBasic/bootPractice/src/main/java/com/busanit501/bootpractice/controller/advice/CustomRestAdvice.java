package com.busanit501.bootpractice.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// RestController 에서 발생하는 각종 exception 에 대해 확인해주는 advice
// 유효성 검사 valid 와 비슷
@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {
    // JSON <-> ReplyDTO 에서 binding 에 문제가 없나 확인
    // BindException 이 발생하면 일단 HttpStatus 를 Expectation_Failed 로 두고
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ResponseEntity<Map<String,String>> handleException(BindException e) {
        // error 의 종류 출력
        log.error("CustomRestAdvice handleException",e);
        log.error(e);

        Map<String,String> errorMap = new HashMap<>();
        if (e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
        }
//        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST); 아래와 같음 이것은 새 인스턴스 생성
        return ResponseEntity.badRequest().body(errorMap);
    }

}
