package com.busanit501.springbootexam.controller;

import com.busanit501.springbootexam.dto.PageRequestDTO;
import com.busanit501.springbootexam.dto.PageResponseDTO;
import com.busanit501.springbootexam.dto.ReviewDTO;
import com.busanit501.springbootexam.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/review")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @Tag(name = "리뷰", description = "mno와 pageRequestDTO 를 받아 페이징된 review 반환")
    @GetMapping("/{mno}")
    public PageResponseDTO<ReviewDTO> getReview(@PathVariable Long mno, PageRequestDTO pageRequestDTO) {
        PageResponseDTO<ReviewDTO> result = reviewService.getReviewList(mno, pageRequestDTO);
        return result;
    }
    @Tag(name = "리뷰", description = "ReviewDTO 를 받아서 update 하고 rno 반환")
    @PutMapping("/update")
    public Map<String, Long> updateReview(@Valid @RequestBody ReviewDTO reviewDTO, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return Map.of("rno", reviewService.edit(reviewDTO));
    }
    @Tag(name = "리뷰", description = "ReviewDTO 를 받아서 insert 하고 rno 반환")
    @PostMapping("/register")
    public Map<String, Long> insertReview(@Valid @RequestBody ReviewDTO reviewDTO, BindingResult bindingResult) throws BindException {
        log.info(reviewDTO + "들어오는 dto 확인");
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return Map.of("rno", reviewService.register(reviewDTO));
    }
    @Tag(name = "리뷰", description = "rno 를 받아서 delete 하고 rno 반환")
    @DeleteMapping("/delete/{rno}")
    public Map<String, Long> deleteReview(@PathVariable Long rno) {
        reviewService.delete(rno);
        return Map.of("rno", rno);
    }
    @Tag(name = "리뷰", description = "모달에서 쓸 댓글의 세부사항 조회")
    @GetMapping("/read/{rno}")
    public ReviewDTO getReviewDetail(@PathVariable Long rno) {
        return reviewService.getReviewDetail(rno);
    }
}
