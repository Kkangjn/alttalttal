package com.alttalttal.mini_project.dto;

import com.alttalttal.mini_project.entity.LoungeEntity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString // field 값 확인할 때 사용
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개 변수로 갖는 생성자
public class LoungeRequestDto { // 로그인 상태에서 작성하기 때문에 username, userid, password 는 필요없겠지?
    private Long id;
    private String base;
    private String content;
}
