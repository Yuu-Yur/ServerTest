package com.busanit501.springbootexam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReviewDTO {
    private Long rno;
    @NotEmpty
    private Long mno;
    @NotEmpty
    private String reviewText;
    @NotEmpty
    private String reviewer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    @JsonIgnore
    private LocalDateTime modDate;
}
