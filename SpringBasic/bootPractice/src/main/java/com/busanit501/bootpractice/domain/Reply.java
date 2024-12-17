package com.busanit501.bootpractice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
// Table 설정, name 은 Reply 기존(entity class name)과 같음
// indexs 안에 @index 생성 순서 중요함. 먼저 쓴 순서대로 n차 정렬
@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
// Board 도 entity 이므로 한번의 테스트에 두번 DB와의 통신이 일어나지 않게 하기 위해 exclude
@ToString(exclude = "board")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 
    private Long rno;

    // @ManyToOne 많은 Reply 엔티티가 하나의 Board 엔티티
    // 즉 다대일 관계 디비에서 보면 외래키 제약조건 지정
    // Board 를 지정했지만 자동으로 Board 의 Id 를 외래키로 지정함
    @ManyToOne(fetch = FetchType.LAZY) //  FetchType.EAGER 즉시로딩 , LAZY 나중에 로딩 즉 사용할 때 로딩
    private Board board;

    @NotEmpty
    private String replyText;
    @NotEmpty
    private String replyer;
}
