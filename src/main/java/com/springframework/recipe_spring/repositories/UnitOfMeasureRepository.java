package com.springframework.recipe_spring.repositories;

import com.springframework.recipe_spring.model.Recipe;
import com.springframework.recipe_spring.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

    Optional<UnitOfMeasure> findByDescription(String description);

     Optional<UnitOfMeasure> findById(Long id);


}
