package com.alttalttal.mini_project.mongo.repository;

import com.alttalttal.mini_project.entity.MongoRecipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongoRecipeRepository extends MongoRepository<MongoRecipe, String> {


    Optional<MongoRecipe> findByRecipeId(Long recipeId);

    List<MongoRecipe> findByZzimUserIdListUserId(Long id);

    boolean existsByRecipeIdAndZzimUserIdListUserId(Long recipeId, Long userId);

}
