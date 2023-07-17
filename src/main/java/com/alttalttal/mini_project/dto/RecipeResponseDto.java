package com.alttalttal.mini_project.dto;

import com.alttalttal.mini_project.entity.MongoRecipe;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeResponseDto {
    private Long recipeId;
    private String name;
    private String explanation;
    private String base;
    private List<IngredientResponseDto> ingredientList;
    private List<MakingDetailResponseDto> makingDetailList;
    private Boolean isUserZzim;
    private Integer countZzim;

    public RecipeResponseDto(MongoRecipe recipe, Boolean isUserZzim, Integer countZzim){
        this.recipeId = recipe.getRecipeId();
        this.name = recipe.getName();
        this.explanation = recipe.getExplanation();
        this.base = recipe.getBase();
        this.ingredientList = recipe.getIngredientList().stream().map(IngredientResponseDto::new).toList();
        this.makingDetailList = recipe.getMakingDetailList().stream().map(MakingDetailResponseDto::new).toList();
        this.isUserZzim = isUserZzim;
        this.countZzim = countZzim;
    }
}
