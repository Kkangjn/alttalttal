package com.alttalttal.mini_project.mongo.repository;

import com.alttalttal.mini_project.entity.MongoRecipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoRecipeRepository extends MongoRepository<MongoRecipe, String> {


    Optional<MongoRecipe> findByRecipeId(Long recipeId);
}
