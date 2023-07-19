package com.alttalttal.mini_project.dto;

import com.alttalttal.mini_project.entity.recipe_component.Ingredient;
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

//    @JsonSerialize(using = CustomSerializer.class)
//    public String getname() {
//        return name;
//    }
//
//    @JsonSerialize(using = CustomSerializer.class)
//    public String getAmount() {
//        return amount;
//    }