package com.springframework.recipe_spring.converter;

import com.springframework.recipe_spring.commands.IngredientCommand;
import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.model.Category;
import com.springframework.recipe_spring.model.Ingredient;
import com.springframework.recipe_spring.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NoteToNoteCommand noteConverter;


    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter,IngredientToIngredientCommand ingredientConverter,
                                 NoteToNoteCommand noteConverter){

        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = noteConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if (recipe == null) {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(recipe.getId());
        command.setCookTime(recipe.getCookTime());
        command.setDescription(recipe.getDescription());
        command.setDirection(recipe.getDirection());
        command.setPrepTime(recipe.getPrepTime());
        command.setSource(recipe.getSource());
        command.setServing(recipe.getServing());
        command.setUrl(recipe.getUrl());
        command.setImage(recipe.getImage());
        command.setNotes(noteConverter.convert(recipe.getNote()));

        if (recipe.getCategories() != null){

            recipe.getCategories().forEach((Category category)->command.getCategoryCommands().add(categoryConverter.convert(category)));
        }

        if (recipe.getIngredients() != null ){

            recipe.getIngredients().forEach((Ingredient ingredient)->command.getIngredientCommands().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
