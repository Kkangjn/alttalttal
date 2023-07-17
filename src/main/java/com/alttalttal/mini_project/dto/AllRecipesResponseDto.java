package com.alttalttal.mini_project.dto;

import com.alttalttal.mini_project.entity.MongoRecipe;
import lombok.Getter;

import java.util.List;

@Getter
public class AllRecipesResponseDto {
    private Long recipeId;
    private String name;
    private String explanation;
    private String base;
    private Integer countZzim;

    public AllRecipesResponseDto(MongoRecipe recipe, Integer countZzim){
        this.recipeId = recipe.getRecipeId();
        this.name = recipe.getName();
        this.explanation = recipe.getExplanation();
        this.base = recipe.getBase();
        this.countZzim = countZzim;
    }
}
