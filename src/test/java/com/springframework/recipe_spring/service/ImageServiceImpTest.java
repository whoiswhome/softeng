package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.controllers.ImageController;
import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImpTest {


    RecipeService recipeService;


    ImageService imageService;

    @Mock
    RecipeRepository recipeRepository;



    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImp(recipeRepository);
    }

    @Test
    public void saveImageFile() throws Exception {
        Long id = 1L;

        MultipartFile file = new MockMultipartFile("imagefile","testing.txt","text/plain","spring framework amir".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(id,file);

        verify(recipeRepository,times(1)).save(argumentCaptor.capture());
    }


}