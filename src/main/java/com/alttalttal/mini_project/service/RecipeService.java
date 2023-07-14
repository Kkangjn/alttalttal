package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.RecipeRequestDto;
import com.alttalttal.mini_project.dto.RecipeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    public RecipeResponseDto getRecipe(Long id, RecipeRequestDto requestDto) {
        return null;
    }
}
