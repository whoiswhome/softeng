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
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {


    private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure){
        this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
    }



    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand command) {

        if (command == null){
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(command.getId());
        ingredient.setAmount(command.getAmount());
        ingredient.setDescription(command.getDescription());
        ingredient.setRecipe(command.getRecipe());

        UnitOfMeasure unitOfMeasure = unitOfMeasureCommandToUnitOfMeasure.convert(command.getUom());

        ingredient.setUom(unitOfMeasure);

        return ingredient;

    }
}
