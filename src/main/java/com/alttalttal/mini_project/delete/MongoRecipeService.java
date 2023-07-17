package com.alttalttal.mini_project.delete;

import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.entity.MongoRecipe;
import com.alttalttal.mini_project.mongo.repository.MongoRecipeRepository;
import com.alttalttal.mini_project.repository.ZzimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoRecipeService {
    private final MongoRecipeRepository mongoRecipeRepository;
    private final ZzimRepository zzimRepository;
    public RecipeResponseDto getRecipe(Long recipeId, Long userId) {
        MongoRecipe recipe = mongoRecipeRepository.findByRecipeId(recipeId).orElseThrow(()-> new IllegalArgumentException("잘못된 접근 입니다."));
        // 유저가 찜 했는지 확인
        Boolean isUserZzim = zzimRepository.existsByRecipeIdAndUserId(recipeId, userId);
        // 찜한 유저수(인기 레시피를 고려해서 만듦)
        Integer countZzim = zzimRepository.countByRecipeId(recipeId);

        return new RecipeResponseDto(recipe, isUserZzim, countZzim);
    }
}
