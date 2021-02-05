package com.springframework.recipe_spring.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;



    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    public Ingredient(){}


    public Ingredient(String description,BigDecimal amount,UnitOfMeasure unitOfMeasure,Recipe recipe){
        this.description = description;
        this.amount = amount;
        this.uom = unitOfMeasure;
        this.recipe = recipe;
    }


    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }


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

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public BigDecimal getAmount(){
        return amount;
    }


    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }


    public Recipe getRecipe(){
        return recipe;
    }


}
