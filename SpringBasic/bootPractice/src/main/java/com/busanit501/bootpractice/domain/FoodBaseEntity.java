package com.busanit501.bootpractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

//VO base
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
abstract class FoodBaseEntity {
    @CreatedDate
    @Column(name = "regDate", updatable = false)
    private Date regDate;
    @LastModifiedDate
    @Column(name = "modDate", updatable = false)
    private Date modDate;
}
