package com.busanit501.bootpractice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // JPA 이용한 엔티티 클래스 annotation
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Board extends BaseEntity {
    // 엔티티 클래스는 테이블 클래스이기 때문에 PK 가 필요함
    @Id
    // 생성 타입 생성 시 어떻게 처리될것인가 아래 변수가
    // mariaDB 는 autoincrement 가 있기 때문에 mariaDB 의 strategy 를 쓰겠다 -> IDENTITY
    // Oracle 같은 경우엔 시퀀스를 쓰기 때문에 SEQUENCE 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(nullable = false, length = 500) // 길이 500자, Null 불가 의 제약 조건
    private String title;
    @Column(nullable = false, length = 2000)
    private String content;
    @Column(nullable = false, length = 50)
    private String author;

    // 베이스 엔티티라는 개념이 있음
    // 타임리프의 베이스와 같이 공통적인 테이블
    // 모든 테이블에 공통으로 들어가는 컬럼은 베이스 엔티티에서 사용


    // 엔티티 클래스는 인스턴스 각각이 해당 DB 의 각 행과 동일함
    // 그래서 getter 만 써서 수정이 어렵게 해둠
    // method 로만 수정이 가능하게 해둠
    public void changeTC(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
