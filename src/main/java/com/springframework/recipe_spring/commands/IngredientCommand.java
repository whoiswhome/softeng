package com.springframework.recipe_spring.commands;

import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.model.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private Recipe recipe;
    private UnitofMeasureCommand uom;
}
