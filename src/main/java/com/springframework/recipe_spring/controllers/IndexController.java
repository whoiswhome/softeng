package com.springframework.recipe_spring.controllers;

import com.springframework.recipe_spring.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class IndexController {


    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @RequestMapping({"/"," ","/index"})
    public String getIndexPage(Model model){

        model.addAttribute("recipes",recipeService.getRecipies());

        return "index";

    }
}
