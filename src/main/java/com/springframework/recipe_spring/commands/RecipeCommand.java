package com.springframework.recipe_spring.commands;

import com.springframework.recipe_spring.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;

    @NotBlank
    @Size(min = 3,max = 255)
    private String description;

    @Min(1)
    @Max(999)
    private Integer prepTime;

    @Min(1)
    @Max(999)
    private Integer cookTime;


    @Min(1)
    @Max(999)
    private Integer serving;

    private String source;

    @URL
    private String url;
    private Byte[] image;

    @NotBlank
    private String direction;

    private NoteCommand notes;
    private Difficulty difficulty;

    private Set<IngredientCommand> ingredientCommands = new HashSet<>();
    private Set<CategoryCommand> categoryCommands = new HashSet<>();




}