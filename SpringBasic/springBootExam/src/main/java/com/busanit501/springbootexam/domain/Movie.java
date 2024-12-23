package com.busanit501.springbootexam.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;
    @Column(nullable = false, length = 50, unique = true)
    private String title;
    @Column(nullable = false, length = 50)
    private String author;
    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private int reservation;
    @Column(nullable = false)
    private LocalDate releaseDate;

    public void changeTARR(String title, String author, int reservation, LocalDate releaseDate) {
        this.title = title;
        this.author = author;
        this.reservation = reservation;
        this.releaseDate = releaseDate;
    }
}
