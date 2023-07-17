package com.alttalttal.mini_project.Dto;

import com.alttalttal.mini_project.entity.User;
import lombok.Getter;

@Getter
public class MypageResponseDto {

        private Long id;
        private String email;
        private String nickname;
//        private LinkedList<> favorite; // 리스트로 어떻게 ResponseDto로 주지...?

        public MypageResponseDto(User user){
            this.id = user.getId();
            this.email = user.getEmail();
            this.nickname = user.getNickname();
        }
}