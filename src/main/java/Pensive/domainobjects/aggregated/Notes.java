package Pensive.domainobjects.aggregated;

import Pensive.domainobjects.Note;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Notes {
    private List<Note> notes;

    public Notes(){
        this.notes = new ArrayList<Note>();
    }

    public void addNote(Note note){
        notes.add(note);
    }
}
