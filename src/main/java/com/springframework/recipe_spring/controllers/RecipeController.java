package com.springframework.recipe_spring.controllers;

import com.springframework.recipe_spring.commands.RecipeCommand;
import com.springframework.recipe_spring.exception.NotFoundException;
import com.springframework.recipe_spring.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){


        model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));

        return "recipe/show";

    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){

        model.addAttribute("recipe",new RecipeCommand());

        return "recipe/recipeform";
    }


    @GetMapping("/recipe/update/{id}")
    public String updateRecipe(@PathVariable String id,Model model){


        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrupdate(@Valid @ModelAttribute("recipe") RecipeCommand recipeCommand, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.error(objectError.toString());
            });

            return "recipe/recipeform";
        }

        RecipeCommand saveCommand = recipeService.saveRecipeCommand(recipeCommand);


        return "redirect:/recipe/show/"+saveCommand.getId();
    }

    @RequestMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable String id){

        recipeService.deleteById(Long.valueOf(id));

        return "index";

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception",exception);
        return modelAndView;
    }

}
