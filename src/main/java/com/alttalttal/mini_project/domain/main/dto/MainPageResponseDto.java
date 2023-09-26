package com.alttalttal.mini_project.domain.main.dto;

import com.alttalttal.mini_project.domain.lounge.dto.LoungeResponseDto;
import com.alttalttal.mini_project.domain.recipe.dto.simpleRecipesResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MainPageResponseDto {
    private List<simpleRecipesResponseDto> recipes;
    private List<LoungeResponseDto> lounges;

    public MainPageResponseDto (List<simpleRecipesResponseDto> simpleRecipesResponseDtoList, List<LoungeResponseDto> loungeResponseDtoList){
        this.recipes = simpleRecipesResponseDtoList;
        this.lounges = loungeResponseDtoList;
    }
}
