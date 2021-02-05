package com.springframework.recipe_spring.controllers;

import com.springframework.recipe_spring.commands.IngredientCommand;
import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.commands.UnitofMeasureCommand;
import com.springframework.recipe_spring.model.UnitOfMeasure;
import com.springframework.recipe_spring.service.IngredientService;
import com.springframework.recipe_spring.service.RecipeService;
import com.springframework.recipe_spring.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService , IngredientService ingredientService,UnitOfMeasureService unitOfMeasureService){
        this.recipeService = recipeService;

        this.unitOfMeasureService = unitOfMeasureService;

        this.ingredientService = ingredientService;
    }



    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listsIngredient(@PathVariable String recipeId, Model model) {

        log.debug("Getting ingredient List for recipe : " + recipeId);

        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";

    }


    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId,@PathVariable String ingredientId,Model model){

        model.addAttribute("ingredient",ingredientService.findByrecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));

        model.addAttribute("uomList",unitOfMeasureService.listAllUom());
        return "recipe/ingredient/ingredientform";

    }


    @RequestMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId,@PathVariable String ingredientId,Model model) {


        model.addAttribute("ingredient",ingredientService.findByrecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));


        return "recipe/ingredient/show";

    }


    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveIngredient(@ModelAttribute IngredientCommand ingredientCommand){

        IngredientCommand saveCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/"+saveCommand.getRecipeId()+"/ingredient/"+saveCommand.getId()+"/show";

    }

    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId,Model model){

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient",ingredientCommand);

        ingredientCommand.setUom(new UnitofMeasureCommand());

        model.addAttribute("uomList",unitOfMeasureService.listAllUom());


        return "/recipe/ingredient/ingredientform";


    }


    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId,@PathVariable String ingredientId){


        ingredientService.deleteById(Long.valueOf(recipeId),Long.valueOf(ingredientId));


        return "redirect:/recipe/"+recipeId+"/ingredients";
    }




}
