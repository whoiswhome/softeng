package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.converter.RecipeCommandToRecipe;
import com.springframework.recipe_spring.converter.RecipeToRecipeCommand;
import com.springframework.recipe_spring.exception.NotFoundException;
import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.repositories.RecipeRepository;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Slf4j
@Service
public class RecipeServiceImp implements RecipeService {




    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImp(RecipeRepository recipeRepository , RecipeCommandToRecipe recipeCommandToRecipe,RecipeToRecipeCommand recipeToRecipeCommand){
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }




    @Override
    public Set<Recipe> getRecipies() {

        log.debug("Im in service");

        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (!recipeOptional.isPresent()){
            throw new NotFoundException("Recipe not found!!!");
        }

        return recipeOptional.get();
    }


    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);


        return recipeToRecipeCommand.convert(savedRecipe);


    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {

        Recipe recipe = findById(l);
        if (recipe == null)
            log.debug("recipeCommand ------->   cant found");
        return recipeToRecipeCommand.convert(recipe);

    }



    @Override
    public void deleteById(Long id) {

        recipeRepository.deleteById(id);

    }
}
