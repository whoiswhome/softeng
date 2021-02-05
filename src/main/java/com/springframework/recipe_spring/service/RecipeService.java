package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.model.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipies();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) throws Exception;

    RecipeCommand findCommandById(Long l);

    void deleteById(Long id);

}
