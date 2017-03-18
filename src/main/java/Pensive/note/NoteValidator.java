package Pensive.note;

import Pensive.prototype.Validator;
import org.springframework.stereotype.Component;

@Component
public class NoteValidator extends Validator<Note> {

    @Override
    public boolean isValidForAdd(Note note) {
        return null != note &&
                null != note.getText() &&
                !note.getText().isEmpty();
    }
}
