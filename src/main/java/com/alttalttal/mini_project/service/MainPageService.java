package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.LoungeResponseDto;
import com.alttalttal.mini_project.dto.MainPageResponseDto;
import com.alttalttal.mini_project.dto.simpleRecipesResponseDto;
import com.alttalttal.mini_project.mongo.repository.MongoRecipeRepository;
import com.alttalttal.mini_project.repository.LoungeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageService {
    private final MongoRecipeRepository mongoRecipeRepository;
    private final LoungeRepository loungeRepository;
    public MainPageResponseDto getMainPage() {
        List<simpleRecipesResponseDto> simpleRecipesResponseDtoList = mongoRecipeRepository.findAll().stream().limit(9).map(simpleRecipesResponseDto::new).toList();
        List<LoungeResponseDto> loungeResponseDtoList = loungeRepository.findAll().stream().map(LoungeResponseDto::new).toList();
        return new MainPageResponseDto(simpleRecipesResponseDtoList, loungeResponseDtoList);
    }
}
