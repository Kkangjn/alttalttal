package com.alttalttal.mini_project.domain.recipe.repository;

import com.alttalttal.mini_project.domain.recipe.entity.MongoRecipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoRecipeRepository extends MongoRepository<MongoRecipe, String> {


    Optional<MongoRecipe> findByRecipeId(Long recipeId);

    List<MongoRecipe> findByZzimUserIdListUserId(Long id);

    boolean existsByRecipeIdAndZzimUserIdListUserId(Long recipeId, Long userId);

}
