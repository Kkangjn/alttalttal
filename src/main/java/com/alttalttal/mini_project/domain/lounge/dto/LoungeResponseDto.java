package com.alttalttal.mini_project.domain.lounge.dto;

import com.alttalttal.mini_project.domain.lounge.entity.LoungeEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LoungeResponseDto {
    private Long id;
    private String base;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;

    public LoungeResponseDto(LoungeEntity loungeEntity){
        this.id = loungeEntity.getId();
        this.base = loungeEntity.getBase();
        this.content = loungeEntity.getContent();
        this.nickname = loungeEntity.getUser().getNickname();
        this.createdAt = loungeEntity.getCreatedAt();
    }
}