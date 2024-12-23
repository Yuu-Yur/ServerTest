package com.busanit501.springbootexam.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
abstract class BaseEntity {
    @CreatedDate
    // 컬럼 annotation 변경불가
    @Column(name = "regDate", updatable = false)
    private LocalDateTime regDate;

    // 마지막으로 변경된 시간 annotation
    @LastModifiedDate
    @Column(name = "modDate", updatable = true)
    private LocalDateTime modDate;
}
