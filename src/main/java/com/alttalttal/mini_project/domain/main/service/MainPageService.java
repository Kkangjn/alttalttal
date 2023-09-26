package com.alttalttal.mini_project.domain.main.service;

import com.alttalttal.mini_project.domain.lounge.dto.LoungeResponseDto;
import com.alttalttal.mini_project.domain.main.dto.MainPageResponseDto;
import com.alttalttal.mini_project.domain.recipe.dto.simpleRecipesResponseDto;
import com.alttalttal.mini_project.domain.recipe.entity.MongoRecipe;
import com.alttalttal.mini_project.domain.recipe.repository.MongoRecipeRepository;
import com.alttalttal.mini_project.domain.lounge.repository.LoungeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageService {
    private final MongoRecipeRepository mongoRecipeRepository;
    private final LoungeRepository loungeRepository;
    public MainPageResponseDto getMainPage() {
        List<MongoRecipe> mongoRecipeList = mongoRecipeRepository.findAll();
        mongoRecipeList.sort(Comparator.comparingInt(mongoRecipe -> mongoRecipe.getZzimUserIdList().size()));
        Collections.reverse(mongoRecipeList);
        List<simpleRecipesResponseDto> simpleRecipesResponseDtoList = mongoRecipeList.stream().limit(9).map(simpleRecipesResponseDto::new).toList();
        List<LoungeResponseDto> loungeResponseDtoList = loungeRepository.findAll().stream().map(LoungeResponseDto::new).toList();
        return new MainPageResponseDto(simpleRecipesResponseDtoList, loungeResponseDtoList);
    }
}
