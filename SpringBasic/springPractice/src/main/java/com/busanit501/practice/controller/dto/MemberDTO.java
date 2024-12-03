package com.busanit501.practice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private long mno;
    private String id;
    private String pw;
    private LocalDate regdate;
    private String uuid;
}
