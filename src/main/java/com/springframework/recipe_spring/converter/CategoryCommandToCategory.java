package com.springframework.recipe_spring.converter;

import com.springframework.recipe_spring.commands.CategoryCommand;
import com.springframework.recipe_spring.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {


    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {

        if (source == null){
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        category.setRecipe(source.getRecipes());

        return category;
    }
}
