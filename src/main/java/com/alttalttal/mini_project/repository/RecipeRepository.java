package com.alttalttal.mini_project.repository;

import com.alttalttal.mini_project.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
