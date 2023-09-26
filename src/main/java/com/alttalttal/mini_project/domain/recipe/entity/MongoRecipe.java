package com.alttalttal.mini_project.domain.recipe.entity;

import com.alttalttal.mini_project.domain.recipe.entity.recipe_component.Ingredient;
import com.alttalttal.mini_project.domain.recipe.entity.recipe_component.MakingDetail;
import com.alttalttal.mini_project.domain.recipe.entity.recipe_component.Zzim;
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
    private List<Zzim> zzimUserIdList;
}
