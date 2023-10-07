package com.alttalttal.mini_project.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String nickname;
    private boolean admin = false;
    private String adminToken = "";
}
