package com.alttalttal.mini_project.entity;

import com.alttalttal.mini_project.dto.RecipeRequestDto;
import com.alttalttal.mini_project.recipe_component.Ingredient;
import com.alttalttal.mini_project.recipe_component.MakingDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "recipe")
@Getter
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
    // {"name":"재료명", "amount":"재료양"}
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Ingredient> IngredientList;
    // {"Making":"칵테일 제조 과정"}
    @ElementCollection(fetch = FetchType.EAGER)
    private List<MakingDetail> makingDetailList;
    @OneToMany(mappedBy = "recipe")
    private List<Zzim> zzim;

    public Recipe (RecipeRequestDto requestDto){
        this.name = requestDto.getName();
        this.base = requestDto.getBase();
        this.explanation = requestDto.getExplanation();
        this.IngredientList = requestDto.getIngredient();
    }
}
