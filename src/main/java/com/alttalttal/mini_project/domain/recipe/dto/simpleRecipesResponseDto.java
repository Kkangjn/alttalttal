package com.alttalttal.mini_project.domain.recipe.dto;

import com.alttalttal.mini_project.domain.recipe.entity.MongoRecipe;
import lombok.Getter;

@Getter
public class simpleRecipesResponseDto {
    private Long recipeId;
    private String name;
    private String explanation;
    private String base;
    private String imageUrl;


    public simpleRecipesResponseDto(MongoRecipe recipe){
        this.recipeId = recipe.getRecipeId();
        this.name = recipe.getName();
        this.explanation = recipe.getExplanation();
        this.base = recipe.getBase();
        this.imageUrl = "https://43.201.127.107:8080/images/" + recipe.getRecipeId() + ".png";
    }
}