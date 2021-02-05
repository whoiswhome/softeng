package com.springframework.recipe_spring.service;

import com.springframework.recipe_spring.commands.UnitofMeasureCommand;
import com.springframework.recipe_spring.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.springframework.recipe_spring.model.UnitOfMeasure;
import com.springframework.recipe_spring.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImpTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;


    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;




    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

        unitOfMeasureService = new UnitOfMeasureServiceImp(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);

    }

    @Test
    public void listAllUom() throws Exception {

        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);

        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(2L);

        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(3L);

        unitOfMeasures.add(unitOfMeasure);
        unitOfMeasures.add(unitOfMeasure1);
        unitOfMeasures.add(unitOfMeasure2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);


        //when
        Set<UnitofMeasureCommand> unitofMeasureCommands = unitOfMeasureService.listAllUom();


        //then
        assertEquals(3,unitofMeasureCommands.size());

        verify(unitOfMeasureRepository,times(1)).findAll();


    }

}