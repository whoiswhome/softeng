package com.springframework.recipe_spring.converter;

import com.springframework.recipe_spring.commands.CategoryCommand;
import com.springframework.recipe_spring.commands.IngredientCommand;
import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NoteCommandToNote noteConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,IngredientCommandToIngredient ingredientConverter,
                                 NoteCommandToNote noteConverter){

        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.noteConverter = noteConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {


        if (source == null){
            return null;
        }

        final Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDescription(source.getDescription());
        recipe.setDirection(source.getDirection());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setSource(source.getSource());
        recipe.setCookTime(source.getCookTime());
        recipe.setServing(source.getServing());
        recipe.setUrl(source.getUrl());

        if(source.getImage()!= null)
            recipe.setImage(source.getImage());


        if (source.getNotes()!= null){
            recipe.setNote(noteConverter.convert(source.getNotes()));
        }




        if (source.getCategoryCommands() != null){
            source.getCategoryCommands().forEach((CategoryCommand categoryCommand)->recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        if (source.getIngredientCommands() != null){
            source.getIngredientCommands().forEach((IngredientCommand ingredientCommand)->recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }

       return recipe;

    }
}
