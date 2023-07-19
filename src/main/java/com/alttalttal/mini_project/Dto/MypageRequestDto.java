package com.alttalttal.mini_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString // field 값 확인할 때 사용
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개 변수로 갖는 생성자
public class MypageRequestDto {
    private String nickname;
}
