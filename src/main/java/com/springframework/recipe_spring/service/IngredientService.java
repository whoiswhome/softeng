package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByrecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteById(Long recipeId,Long ingredientId);
}
