package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.entity.Recipe;
import com.alttalttal.mini_project.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public RecipeResponseDto getRecipe(Long id, Long userId) {
        Recipe recipe = findRecipeById(id);
        // 유저가 찜 했는지 확인
        Boolean isUserZzim = recipe.getZzim().stream().anyMatch(zzim -> zzim.getUserId() == userId);
        // 찜한 유저수(인기 레시피를 고려해서 만듦)
        Integer countZzim = recipe.getZzim().size();

        return new RecipeResponseDto(recipe, isUserZzim, countZzim);
    }

    private Recipe findRecipeById(Long id){
        return recipeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("잘못된 접근입니다.")
        );
    }
}
