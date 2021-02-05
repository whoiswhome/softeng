package com.springframework.recipe_spring.commands;

import com.springframework.recipe_spring.model.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;
    private String description;
    private Set<Recipe> recipes;

}
