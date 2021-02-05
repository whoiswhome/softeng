package com.springframework.recipe_spring.converter;

import com.springframework.recipe_spring.commands.UnitofMeasureCommand;
import com.springframework.recipe_spring.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitofMeasureCommand> {


    @Synchronized
    @Nullable
    @Override
    public UnitofMeasureCommand convert(UnitOfMeasure unitOfMeasure) {


        if (unitOfMeasure == null){
            return null;
        }

        final UnitofMeasureCommand uomCommand = new UnitofMeasureCommand();
        uomCommand.setId(unitOfMeasure.getId());
        uomCommand.setDescription(unitOfMeasure.getDescription());

        return uomCommand;
    }



}
