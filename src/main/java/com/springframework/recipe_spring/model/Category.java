package com.springframework.recipe_spring.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipe;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setRecipe(Set<Recipe> recipe){
        this.recipe = recipe;
    }

    public Set<Recipe> getRecipe(){
        return recipe;
    }


}
