package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.commands.IngredientCommand;
import com.springframework.recipe_spring.converter.IngredientCommandToIngredient;
import com.springframework.recipe_spring.converter.IngredientToIngredientCommand;
import com.springframework.recipe_spring.converter.UnitOfMeasureCommandToUnitOfMeasure;
import com.springframework.recipe_spring.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.springframework.recipe_spring.model.Ingredient;
import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.repositories.RecipeRepository;
import com.springframework.recipe_spring.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImpTest {


    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    IngredientService ingredientService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;



    @Mock
    RecipeRepository recipeRepository;


    public IngredientServiceImpTest(){

        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());

    }

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImp(recipeRepository,unitOfMeasureRepository,ingredientCommandToIngredient,ingredientToIngredientCommand);

    }


    @Test
    public void findByRecipeIdAndIngredientId() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(2L);

        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient1);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        IngredientCommand ingredientCommand = ingredientService.findByrecipeIdAndIngredientId(1L,1L);

        verify(recipeRepository,times(1)).findById(anyLong());

    }
}