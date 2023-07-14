package com.alttalttal.mini_project.recipe_component;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ingredient {
    private String name;
    private String amount;
}
