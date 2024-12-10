package com.busanit501.bootpractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

// 모든 테이블의 공통 요소 라는 annotation
@MappedSuperclass
// 모든 엔티티는 이 엔티티를 요소로 가지므로
// 이 엔티티를 리스너로 시스템에 등록 하면
// 다른 엔티티 클래스를 주입하는 것과 같음
@EntityListeners(AuditingEntityListener.class)
// 시스템에 직결되므로 불변성 필요 , getter 만
@Getter
// 유연하게 사용 하기 위해 추상클래스, 인터페이스가 아닌 이유는 다중 상속 하지 않기 위해
abstract class BaseEntity {

    // 만들어진 시간 annotation
    @CreatedDate
    // 컬럼 annotation 변경불가
    @Column(name = "regDate", updatable = false)
    private Date regDate;

    // 마지막으로 변경된 시간 annotation
    @LastModifiedDate
    @Column(name = "modDate", updatable = false)
    private Date modDate;
}
