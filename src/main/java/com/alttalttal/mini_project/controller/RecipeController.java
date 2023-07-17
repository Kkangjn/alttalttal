package com.alttalttal.mini_project.controller;

import com.alttalttal.mini_project.dto.AllRecipesResponseDto;
import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;
    final Long userId = 2L; // 유저 테이블이 없어서 임의로 값을 넣어줌
    // security를 쓴다면 @AuthenticationPrincipal UserDetails userDetails
    // userDetails.getName? getId로 처리할듯?

    // 만약 로그인을 하지 않았다면 fi(userDeails == null) {userId = 0;} 으로 service로 보내서 처리할듯!

    @GetMapping()
    private List<AllRecipesResponseDto> getAllRecipes(){
        return recipeService.getAllRecipes();
    }

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
