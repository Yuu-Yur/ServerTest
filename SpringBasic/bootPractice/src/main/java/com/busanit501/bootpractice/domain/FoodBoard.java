package com.busanit501.bootpractice.domain;

import jakarta.persistence.*;
import lombok.*;

//VO
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class FoodBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 20)
    private String shop;
    @Column(nullable = false)
    private int price;
    @Builder.Default
    private boolean revisit = false;

    public void changeNSPR(String name, String shop, int price, boolean revisit) {
        this.name = name;
        this.shop = shop;
        this.price = price;
        this.revisit = revisit;
    }
}
