package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    @GetMapping("/{id}")
    public RecipeResponseDto getRecipe(@PathVariable Long id){
        Long userId = 2L; // 유저 테이블이 없어서 임의로 값을 넣어줌
        return recipeService.getRecipe(id, userId);
    }
}
