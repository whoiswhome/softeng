package com.springframework.recipe_spring.converter;

import com.springframework.recipe_spring.commands.UnitofMeasureCommand;
import com.springframework.recipe_spring.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitofMeasureCommand, UnitOfMeasure> {


    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitofMeasureCommand source) {

        if (source == null){
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }
}
