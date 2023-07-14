package com.alttalttal.mini_project.entity;

import com.alttalttal.mini_project.dto.RecipeRequestDto;
import com.alttalttal.mini_project.recipe_component.Ingredient;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "recipe")
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "base", nullable = false)
    private String base;
    @Column(name = "explanation", nullable = false)
    private String explanation;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Ingredient> ingredientList;
    @Column(name = "recipe", nullable = false)
    private String recipe;

    public Recipe (RecipeRequestDto requestDto){
        this.name = requestDto.getName();
        this.base = requestDto.getBase();
        this.explanation = requestDto.getExplanation();
        this.ingredientList = requestDto.getIngredient();
        this.recipe = requestDto.getRecipe();
    }

}
