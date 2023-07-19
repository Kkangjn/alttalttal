package com.alttalttal.mini_project.dto;

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
