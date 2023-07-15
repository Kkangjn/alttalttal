package com.alttalttal.mini_project.dto;

import com.alttalttal.mini_project.entity.Recipe;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeResponseDto {
    private String name;
    private String explanation;
    private String base;
    private List<IngredientResponseDto> ingredientList;
    private List<MakingDetailResponseDto> makingDetailList;
    private Boolean isUserZzim;
    private Integer countZzim;

    public RecipeResponseDto(Recipe recipe, Boolean isUserZzim, Integer countZzim){
        this.name = recipe.getName();
        this.explanation = recipe.getExplanation();
        this.base = recipe.getBase();
        this.ingredientList = recipe.getIngredientList().stream().map(IngredientResponseDto::new).toList();
        this.makingDetailList = recipe.getMakingDetailList().stream().map(MakingDetailResponseDto::new).toList();
        this.isUserZzim = isUserZzim;
        this.countZzim = countZzim;
    }
}
