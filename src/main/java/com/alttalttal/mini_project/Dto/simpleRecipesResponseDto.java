package com.alttalttal.mini_project.dto;

import com.alttalttal.mini_project.entity.MongoRecipe;
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
        this.imageUrl = "http://localhost:8080/images/" + recipe.getRecipeId() + ".png";
    }
}