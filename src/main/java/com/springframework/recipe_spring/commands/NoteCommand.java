package com.springframework.recipe_spring.commands;

import com.springframework.recipe_spring.model.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NoteCommand {

    private Long id;
    private String recipeNotes;
    private Recipe recipe;
}
