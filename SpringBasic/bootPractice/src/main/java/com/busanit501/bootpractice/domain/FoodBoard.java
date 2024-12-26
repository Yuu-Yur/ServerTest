package com.busanit501.bootpractice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//VO
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "imageSet")
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

    @OneToMany(mappedBy = "foodBoard",cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    // 여기서 foodBoard 는 자식테이블 아래의 FoodImage 의 필드 foodBoard
    // 이 mappedBy 설정을 하면 중간 테이블 없이 데이터베이스처럼 자식 테이블 기준으로 작동함
    // cascade 영속성 설정, 하위테이블이 상위테이블의 변화에 의존함 어떻게? 를 설정
    // 이렇게 영속성을 설정하면 부모 테이블 수정 삭제 시 하위 테이블에도 영향이 그대로 감
    // orphanRemoval 옵션으로 부모테이블 참조가 없어진 자식테이블을 바로 삭제할 수 있음
    @Builder.Default
    private Set<FoodImage> imageSet = new HashSet<>(); // Set 은 중복이 안되는 리스트
    // 이걸로 FoodBoard 에서 FoodImage 조회가능
    // 기존엔 이게 없어서 FoodBoardRepository 에서 leftJoin 으로 FoodReply 를 조인해서 조회했음
    // 양측에서 설정하면 사실상 다대다 관계가 되어 자동으로 중간 테이블을 생성해줌
    /*
    아래와 같이 FoodBoard 테이블과 FoodImage 테이블과 함께 중간 테이블을 만들어줌
    Hibernate:
    create table food_board (
            price integer not null,
            revisit bit not null,
            fno bigint not null auto_increment,
            mod_date datetime(6),
    reg_date datetime(6),
    name varchar(20) not null,
    shop varchar(20) not null,
    primary key (fno)
    ) engine=InnoDB
    Hibernate:
    create table food_board_image_set (
            food_board_fno bigint not null,
            image_set_uuid varchar(255) not null,
    primary key (food_board_fno, image_set_uuid)
    ) engine=InnoDB
    Hibernate:
    create table food_image (
            ord integer not null,
            food_board_fno bigint,
            file_name varchar(255),
    uuid varchar(255) not null,
    primary key (uuid)
    ) engine=InnoDB
    */
    

    public void changeNSPR(String name, String shop, int price, boolean revisit) {
        this.name = name;
        this.shop = shop;
        this.price = price;
        this.revisit = revisit;
    }

    public void addImage(String uuid, String fileName) {
        imageSet.add(FoodImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .foodBoard(this)
                .ord(imageSet.size())
                .build()
        );
    }
    public void clearImages() {
        // 자식 테이블이 참조하는 키 를 null 로 바꿔버림
        // 즉 참조할 부모 테이블이 없어져 고아 테이블이 됨
        imageSet.forEach(foodImage -> foodImage.sFoodBoard(null));
        this.imageSet.clear();
    }
}
