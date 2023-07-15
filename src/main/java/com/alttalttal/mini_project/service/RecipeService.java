package com.alttalttal.mini_project.service;

import com.alttalttal.mini_project.dto.RecipeResponseDto;
import com.alttalttal.mini_project.entity.Recipe;
import com.alttalttal.mini_project.entity.Zzim;
import com.alttalttal.mini_project.repository.RecipeRepository;
import com.alttalttal.mini_project.repository.ZzimRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final ZzimRepository zzimRepository;
    public RecipeResponseDto getRecipe(Long id, Long userId) {
        Recipe recipe = findRecipeById(id);
        // 유저가 찜 했는지 확인
        Boolean isUserZzim = recipe.getZzim().stream().anyMatch(zzim -> zzim.getUserId() == userId);
        // 찜한 유저수(인기 레시피를 고려해서 만듦)
        Integer countZzim = recipe.getZzim().size();

        return new RecipeResponseDto(recipe, isUserZzim, countZzim);
    }

    public ResponseEntity<String> createZzim(Long id, Long userId) {
        Recipe recipe = findRecipeById(id);
        Zzim zzim = new Zzim(recipe, userId);
        zzimRepository.save(zzim);
        return ResponseEntity.ok("찜 성공");
    }

    @Transactional // 쿼리메서드를 사용해서 Transactional이 필요한건가?
    public ResponseEntity<String> deleteZzim(Long id, Long userId) {
        zzimRepository.deleteByUserIdAndRecipeId(id, userId); // select + delete
        return ResponseEntity.ok("찜 삭제");
    }

    private Recipe findRecipeById(Long id){
        return recipeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("잘못된 접근입니다.")
        );
    }
}
