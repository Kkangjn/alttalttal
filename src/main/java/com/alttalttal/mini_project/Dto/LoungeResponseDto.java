package com.alttalttal.mini_project.Dto;

import com.alttalttal.mini_project.entity.LoungeEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LoungeResponseDto {
    private Long id;
    private String base;
    private String content;
    private LocalDateTime createdAt;

    public LoungeResponseDto(LoungeEntity loungeEntity){
        this.id = loungeEntity.getId();
        this.base = loungeEntity.getBase();
        this.content = loungeEntity.getContent();
    }
}