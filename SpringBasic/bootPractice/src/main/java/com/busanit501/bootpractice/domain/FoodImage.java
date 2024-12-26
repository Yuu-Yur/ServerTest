package com.busanit501.bootpractice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class FoodImage implements Comparable<FoodImage>{ // 첨부 이미지, Comparable 은 CompareTo 로 순서정할 때 씀
    @Id
    private String uuid;

    private String fileName;
    private int ord; // 첨부 이미지의 크기로 order 정하기
    @ManyToOne
    private FoodBoard foodBoard;
    // 이걸로 FoodImage 에서 FoodBoard 조회 가능
    
    @Override
    public int compareTo(FoodImage o) {
        // 아래값이 양수면 오름차순, 음수면 내림차순
        // sort() 의 설정이라고 볼 수 있음
        return this.ord - o.ord;
    }

    // 불변성때문에 @Setter 은 못쓰고 꼭 필요한 FoodBoard 만 정의
    public void sFoodBoard(FoodBoard foodBoard) {
        this.foodBoard = foodBoard;
    }
}
