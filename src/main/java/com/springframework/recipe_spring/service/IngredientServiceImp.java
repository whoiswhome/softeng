package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.commands.IngredientCommand;
import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.converter.IngredientCommandToIngredient;
import com.springframework.recipe_spring.converter.IngredientToIngredientCommand;
import com.springframework.recipe_spring.model.Ingredient;
import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.model.UnitOfMeasure;
import com.springframework.recipe_spring.repositories.RecipeRepository;
import com.springframework.recipe_spring.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImp implements IngredientService {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImp(RecipeRepository recipeRepository,UnitOfMeasureRepository unitOfMeasureRepository,
                                IngredientCommandToIngredient ingredientCommandToIngredient,
                                IngredientToIngredientCommand ingredientToIngredientCommand){

        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;

    }


    @Override
    public IngredientCommand findByrecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (!optionalRecipe.isPresent())
            System.err.println("recipe is not found");

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> OptionalIngredientCommand  = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId() == ingredientId)
                .map( ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();


        if (!OptionalIngredientCommand.isPresent()){
            log.debug("ingredient could'nt find");
        }

        return OptionalIngredientCommand.get();
    }


    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {


        Optional<Ingredient> ingredientOptional;

        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOptional.isPresent()){
            log.error("recipe not found");
            return new IngredientCommand();
        }

        Recipe recipe = recipeOptional.get();


            ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();


        if (ingredientOptional.isPresent()){
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setAmount(ingredientCommand.getAmount());
            ingredient.setDescription(ingredientCommand.getDescription());
            Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findById(ingredientCommand.getUom().getId());

            if (uom.isPresent()) ingredient.setUom(uom.get());

        }else{

            Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
            ingredientCommand.setRecipe(recipe);
            recipe.addIngredient(ingredient);

        }

        Recipe recipeSave = recipeRepository.save(recipe);

        Optional<Ingredient> savedIngredientOptional  = recipeSave.getIngredients().stream()
                .filter(recipeIngredient -> recipeIngredient.getId().equals(ingredientCommand.getId()))
                .findFirst();

        if (!savedIngredientOptional.isPresent()) {

            savedIngredientOptional = recipeSave.getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
                .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
                .findFirst();

        }

        Ingredient ingredient = savedIngredientOptional.get();

        return ingredientToIngredientCommand.convert(ingredient);


    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {

        Recipe recipe;

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isPresent()) {

            recipe = recipeOptional.get();

        }else { return; }

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                 .filter(ingredient -> ingredient.getId().equals(ingredientId))
                 .findFirst();

        if (ingredientOptional.isPresent()){
            Ingredient ingredient = ingredientOptional.get();
            ingredient.setRecipe(null);
            recipe.getIngredients().remove(ingredient);
        }

        recipeRepository.save(recipe);


    }


}
