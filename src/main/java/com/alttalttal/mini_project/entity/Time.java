package com.alttalttal.mini_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
// 시간 정보를 다루는 클래스를 따로 만들어둔 이유는 여기저기서 쓸 수 있게 하기 위해서입니다.
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
@Getter

public class Time {
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedTime;
}