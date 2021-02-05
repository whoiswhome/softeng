package com.springframework.recipe_spring.bootstrap;


import com.springframework.recipe_spring.model.*;
import com.springframework.recipe_spring.repositories.CategoryRepository;
import com.springframework.recipe_spring.repositories.RecipeRepository;
import com.springframework.recipe_spring.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository,RecipeRepository recipeRepository,UnitOfMeasureRepository uomRepository){
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = uomRepository;
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>(2);

        log.debug("Loading bootstrap data...");

        Optional<UnitOfMeasure> cupUomOptinal = unitOfMeasureRepository.findByDescription("cup");

        if (!cupUomOptinal.isPresent()){
            throw new RuntimeException("Exception uom not found");
        }

        Optional<UnitOfMeasure> peachUomOptional = unitOfMeasureRepository.findByDescription("peach");

        if (!peachUomOptional.isPresent()){
            throw new RuntimeException("Exception uom not found");
        }


        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");

        if (!ounceUomOptional.isPresent()){
            throw new RuntimeException("Exception uom not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");


        if (!dashUomOptional.isPresent()){
            throw new RuntimeException("Exception uom not found");
        }

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("tablespoon");


        if (!tablespoonOptional.isPresent()){
            throw new RuntimeException("Exception uom not found");
        }

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("teaspoon");


        if (!teaspoonOptional.isPresent()){
            throw new RuntimeException("Exception uom not found");
        }

        //get measures
        UnitOfMeasure cupUom = cupUomOptinal.get();
        UnitOfMeasure peachUom = peachUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure tableUpm = tablespoonOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonOptional.get();


        Optional<Category> americanCatOptional = categoryRepository.findByDescription("American");

        if (!americanCatOptional.isPresent()){
            throw new RuntimeException("Exception Category not found");
        }

        Optional<Category> mexicanCatOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCatOptional.isPresent()){
            throw new RuntimeException("Exception  not found");
        }

        //get Category
        Category americanCategory = americanCatOptional.get();
        Category mexicancategory = mexicanCatOptional.get();

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("perfect food");
        guacRecipe.setCookTime(0);
        guacRecipe.setPrepTime(10);
        guacRecipe.setDifficulty(Difficulty.easy);
        guacRecipe.setDirection("This is very easy food and I love this \n" +
                "I cut avocado and remove flesh and " +
                "sadasdns akld nsak sadlnas askdnsa klnasd world is very strange");

        Note guacNotes = new Note();

        guacNotes.setRecipeNote("I love this food for everyTime in Summer but you can serve in winter and "+
                "aslkdas ndjj skdnsjd nnknsdd ajnsdjas world is very strange");

        guacRecipe.setNote(guacNotes);

        guacRecipe.getIngredients().add(new Ingredient("cup",new BigDecimal(2),cupUom,guacRecipe));

        guacRecipe.getIngredients().add(new Ingredient("peach",new BigDecimal(3),peachUom,guacRecipe));

        guacRecipe.getIngredients().add(new Ingredient("Ounce",new BigDecimal(4),ounceUom,guacRecipe));


        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicancategory);

        recipes.add(guacRecipe);


        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy chicken taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.medium);

        tacosRecipe.setDirection("sdksjd jkasnd ajsdjas jkansdja guyg yugdasg "+
                "knskajnd najsnd jasndjas reijge oeprgk pwqepqw erogerop uiqwhehq qjiweqowj");

        Note tacosnote = new Note();

        tacosnote.setRecipeNote("asldklsd jkjklj kljugu guuygyg ioij huihiuh uhiuhiuh hiuhihu "+
                "opowe pqoweiqw qpwoieqwoi qpoweoq wejofjew jeiwjfiow iwjefijew iojfewj ");
        tacosRecipe.setNote(tacosnote);


        tacosRecipe.getIngredients().add(new Ingredient("Dash",new BigDecimal(5),dashUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("teaspoon",new BigDecimal(6),teaspoonUom,tacosRecipe));
        tacosRecipe.getIngredients().add(new Ingredient("tablespoon",new BigDecimal(2),tableUpm,tacosRecipe));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicancategory);

        recipes.add(tacosRecipe);



        return recipes;


    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recipeRepository.saveAll(getRecipes());
    }
}
