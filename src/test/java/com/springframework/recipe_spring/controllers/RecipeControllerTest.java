package com.springframework.recipe_spring.controllers;

import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServlet;
import javax.swing.text.View;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    RecipeController recipeController;


    MockMvc mockMvc;


    @Mock
    RecipeService recipeService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController(recipeService);

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

    }

    @Test
    public void showById() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);


        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {

        RecipeCommand command = new RecipeCommand();

        mockMvc.perform(post("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));

    }

    @Test
    public void testPostNewRecipeForm() throws Exception {

        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        System.out.println("Hellooo");
        when(recipeService.saveRecipeCommand(any())).thenReturn(command);

        System.err.println("after Hellooo");

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description","some string"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/show/2"));

        System.err.println("after after hello");
    }


    @Test
    public void update() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);


        when(recipeService.findById(recipe.getId())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"));
    }


    @Test
    public void handleException() throws Exception {

        mockMvc.perform(get("/recipe/show/asd"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("400error"));
    }
}