package com.alttalttal.mini_project.domain.recipe.dto;

import com.alttalttal.mini_project.domain.recipe.entity.recipe_component.Ingredient;
import lombok.Getter;

@Getter
public class IngredientResponseDto {
    private String name;
    private String amount;

    public IngredientResponseDto(Ingredient ingredient){
        this.name = ingredient.getName();
        this.amount = ingredient.getAmount();
    }
}
