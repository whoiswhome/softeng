package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.commands.UnitofMeasureCommand;
import com.springframework.recipe_spring.converter.UnitOfMeasureCommandToUnitOfMeasure;
import com.springframework.recipe_spring.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.springframework.recipe_spring.model.UnitOfMeasure;
import com.springframework.recipe_spring.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImp implements UnitOfMeasureService{


    private UnitOfMeasureRepository unitOfMeasureRepository;
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter;

    public UnitOfMeasureServiceImp(UnitOfMeasureRepository unitOfMeasureRepository,UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter){

        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureConverter = unitOfMeasureConverter;

    }


    @Override
    public Set<UnitofMeasureCommand> listAllUom() {

        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(),false)
                .map(unitOfMeasureConverter::convert)
                .collect(Collectors.toSet());
    }
}
