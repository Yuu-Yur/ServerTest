package com.busanit501.practice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {
    private Long tno;
    @NotEmpty
    private String title;
    @Future
    private LocalDate dueDate;
    private boolean finished;
    private String writer;
}
