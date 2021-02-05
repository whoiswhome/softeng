package com.springframework.recipe_spring.repositories;

import com.springframework.recipe_spring.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {



}
