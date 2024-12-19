package com.busanit501.bootpractice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FoodReply", indexes = {
        @Index(name = "idx_foodreply_foodboard_fno", columnList = "foodBoard_fno")
})
public class FoodReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    private FoodBoard foodBoard;

    @NotEmpty
    private String replyAuthor;
    @NotEmpty
    private String replyText;

    public void foodBoardSetter(FoodBoard foodBoard) {
        this.foodBoard = foodBoard;
    }
}
