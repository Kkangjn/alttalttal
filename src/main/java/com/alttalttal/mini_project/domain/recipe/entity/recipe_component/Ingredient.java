package com.alttalttal.mini_project.domain.recipe.entity.recipe_component;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
public class Ingredient {
    private String name;
    private String amount;
}
