package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.commands.UnitofMeasureCommand;
import com.springframework.recipe_spring.model.UnitOfMeasure;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitofMeasureCommand> listAllUom();
}
