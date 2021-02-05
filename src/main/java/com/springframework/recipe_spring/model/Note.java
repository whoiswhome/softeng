package com.springframework.recipe_spring.model;

import javax.persistence.*;


@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String recipeNote;

    @OneToOne
    private Recipe recipe;


    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setRecipeNote(String recipeNote){
        this.recipeNote = recipeNote;
    }

    public String getRecipeNote(){
        return recipeNote;
    }

    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }

    public Recipe getRecipe(){
        return recipe;
    }

}
