package com.busanit501.helloworld.jdbcex.DTO;

import lombok.*;

import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private int mno;
    private String id;
    private String pw;
    private Date regdate;
    private String uuid;
}
