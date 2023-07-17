package com.alttalttal.mini_project.delete;

import com.alttalttal.mini_project.dto.RecipeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class MongoRecipeController {
    private final MongoRecipeService mongoRecipeService;
    final Long userId = 2L; // 유저 테이블이 없어서 임의로 값을 넣어줌
    // security를 쓴다면 @AuthenticationPrincipal UserDetails userDetails
    // userDetails.getName? getId로 처리할듯?

    // 만약 로그인을 하지 않았다면 fi(userDeails == null) {userId = 0;} 으로 service로 보내서 처리할듯!
    @GetMapping("/mongo/{recipeId}")
    public RecipeResponseDto getRecipe(@PathVariable Long recipeId){
        return mongoRecipeService.getRecipe(recipeId, userId);
    }

}
