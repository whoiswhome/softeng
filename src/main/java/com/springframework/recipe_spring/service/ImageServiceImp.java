package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImp implements ImageService {


    private RecipeRepository recipeRepository;

    public ImageServiceImp(RecipeRepository recipeRepository) {

        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) throws IOException {

        Recipe recipe = recipeRepository.findById(Long.valueOf(recipeId)).get();

        Byte[] bytes = new Byte[file.getBytes().length];

        int i = 0;
        for(byte b : file.getBytes()){
            bytes[i++] = b;
        }
        recipe.setImage(bytes);
        recipeRepository.save(recipe);

    }
}
