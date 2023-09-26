package com.alttalttal.mini_project.domain.user.dto;

import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private boolean flag;
    private String nickname;

    public UserInfoResponseDto(boolean flag, String nickname){
        this.flag = flag;
        this.nickname = nickname;
    }
}
