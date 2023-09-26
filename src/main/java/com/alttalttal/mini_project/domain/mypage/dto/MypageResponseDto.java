package com.alttalttal.mini_project.domain.mypage.dto;

import com.alttalttal.mini_project.domain.lounge.dto.LoungeResponseDto;
import com.alttalttal.mini_project.domain.user.entity.User;
import com.alttalttal.mini_project.domain.recipe.dto.simpleRecipesResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MypageResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private List<LoungeResponseDto> myLounges;
    //        private LinkedList<> favorite; // 리스트로 어떻게 ResponseDto로 주지...?
    private List<simpleRecipesResponseDto> zzimRecipes;

    public MypageResponseDto(User user, List<simpleRecipesResponseDto> simpleRecipesResponseDtoList){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.myLounges = user.getLoungeEntityList().stream().map(LoungeResponseDto::new).toList();
        this.zzimRecipes = simpleRecipesResponseDtoList;
    }
}