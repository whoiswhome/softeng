package com.springframework.recipe_spring.converter;

import com.springframework.recipe_spring.commands.IngredientCommand;
import com.springframework.recipe_spring.commands.UnitofMeasureCommand;
import com.springframework.recipe_spring.model.Ingredient;

import com.springframework.recipe_spring.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand){
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }




    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {

        if (ingredient == null){
            return null;
        }

        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setRecipe(ingredient.getRecipe());

        UnitofMeasureCommand unitofMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.convert(ingredient.getUom());

        ingredientCommand.setUom(unitofMeasureCommand);


        return ingredientCommand;

    }
}
