package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.AllRecipesResponseDto;
import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.entity.MongoRecipe;
import com.alttalttal.mini_project.entity.Zzim;
import com.alttalttal.mini_project.mongo.repository.MongoRecipeRepository;
import com.alttalttal.mini_project.repository.ZzimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final ZzimRepository zzimRepository;
    private final MongoRecipeRepository mongoRecipeRepository;
    public List<AllRecipesResponseDto> getAllRecipes() {
        List<AllRecipesResponseDto> allRecipesResponseDtoList = new ArrayList<>();
        List<MongoRecipe> mongoRecipeList = mongoRecipeRepository.findAll();
        for (MongoRecipe recipe : mongoRecipeList) {
            Integer countZzim = zzimRepository.countByRecipeId(recipe.getRecipeId());
            allRecipesResponseDtoList.add(new AllRecipesResponseDto(recipe, countZzim));
        }
        return allRecipesResponseDtoList.stream().sorted(Comparator.comparing(AllRecipesResponseDto::getCountZzim).reversed()).toList();
    }

    public RecipeResponseDto getRecipe(Long recipeId, Long userId) {
        MongoRecipe recipe = mongoRecipeRepository.findByRecipeId(recipeId).orElseThrow(()-> new IllegalArgumentException("잘못된 접근 입니다."));
        // 유저가 찜 했는지 확인
        Boolean isUserZzim = zzimRepository.existsByRecipeIdAndUserId(recipeId, userId);
        // 찜한 유저수(인기 레시피를 고려해서 만듦)
        Integer countZzim = zzimRepository.countByRecipeId(recipeId);

        return new RecipeResponseDto(recipe, isUserZzim, countZzim);
    }

    public ResponseEntity<String> createZzim(Long recipeId, Long userId) {
        // 검증을 추가(이미 찜한 상태인지 확인)
        if(zzimRepository.existsByRecipeIdAndUserId(recipeId, userId)){
            throw new IllegalArgumentException("잘못된 접근입니다.");

        }
        Zzim zzim = new Zzim(recipeId, userId);
        zzimRepository.save(zzim);
        return ResponseEntity.ok("찜 성공");
    }

    public ResponseEntity<String> deleteZzim(Long recipeId, Long userId) {
        Zzim zzim = zzimRepository.findByRecipeIdAndUserId(recipeId, userId).orElseThrow(()->
                new IllegalArgumentException("잘못된 접근입니다.")
        );
        zzimRepository.delete(zzim);
        return ResponseEntity.ok("찜 삭제");
    }
}
