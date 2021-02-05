package com.springframework.recipe_spring.converter;

import com.springframework.recipe_spring.commands.NoteCommand;
import com.springframework.recipe_spring.model.Note;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNote implements Converter<NoteCommand, Note> {

    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand command) {

        if (command == null) {
            return null;
        }


        final Note note = new Note();
        note.setId(command.getId());
        note.setRecipeNote(command.getRecipeNotes());
        note.setRecipe(command.getRecipe());

        return note;
    }
}
