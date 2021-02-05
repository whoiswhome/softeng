package com.springframework.recipe_spring.controllers;

import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.converter.RecipeToRecipeCommand;
import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.service.IngredientService;
import com.springframework.recipe_spring.service.RecipeService;
import com.springframework.recipe_spring.service.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    IngredientController ingredientController;

    @Mock
    IngredientService ingredientService;

    MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        ingredientController = new IngredientController(recipeService,ingredientService,unitOfMeasureService);

        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

    }


    @Test
    public void showIngredient() throws Exception {

        RecipeCommand recipeCommand = new RecipeCommand();

        when(recipeService.findCommandById(any())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredient"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));




    }
}