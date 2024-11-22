package com.busanit501.helloworld.jdbcex.DTO;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

//VO(Value Object) 실제 DB와 일치 (컬럼)
//Service <-> DB의 사이에서 사용
//DTO(Data Transfer Object) VO중 사용할 몇가지를 선택하여 만들기
//Servlet <-> Service
//Servlet <-> View 등 DB를 제외한 Backend 사이에서 사용

//@Getter
//@Setter
//@ToString
@Data
@Builder
public class TodoVO {
    private long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
