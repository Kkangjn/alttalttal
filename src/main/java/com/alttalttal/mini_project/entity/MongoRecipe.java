package com.alttalttal.mini_project.entity;

import com.alttalttal.mini_project.delete.recipe_component.Ingredient;
import com.alttalttal.mini_project.delete.recipe_component.MakingDetail;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recipe")
@Getter
@NoArgsConstructor
public class MongoRecipe {
    @Id
    private String id;
    private Long recipeId;
    private String name;
    private String base;
    private String explanation;
    private List<Ingredient> ingredientList;
    private List<MakingDetail> makingDetailList;
}
