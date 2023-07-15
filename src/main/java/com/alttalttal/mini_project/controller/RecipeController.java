package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    final Long userId = 2L; // 유저 테이블이 없어서 임의로 값을 넣어줌
    @GetMapping("/{id}")
    public RecipeResponseDto getRecipe(@PathVariable Long id){
        return recipeService.getRecipe(id, userId);
    }
    @PostMapping("/{id}")
    public ResponseEntity<String> createZzim(@PathVariable Long id){
        return recipeService.createZzim(id, userId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteZzim(@PathVariable Long id){
        return recipeService.deleteZzim(id, userId);
    }
}
