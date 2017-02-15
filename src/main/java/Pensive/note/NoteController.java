package Pensive.note;

import Pensive.prototype.Controller;
import Pensive.prototype.PatchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ENDPOINT                                               HTTP METHOD  DESCRIPTION                                  IMPLEMENTED
 * <p>
 * /notes[?users=sharmani]                                 GET         get all the notes with a specific criteria   Y (implement sorting and filter)
 * /notes                                                  POST        create a note                                Y
 * /notes/noteId                                           DELETE      delete a note                                Y
 * /notes/noteId                                           PATCH       update the text in a note                    Y
 * /notes/noteId                                           PATCH       update the paragraph id's in a note          Y
 */

@RestController
@RequestMapping("/notes")
public class NoteController extends Controller<Note, NoteValidator, NoteRepository, NoteService> {

    @Autowired
    public NoteController(NoteService noteService) {
        super(noteService);
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.PATCH)
    public Note updateNote(@PathVariable String noteId, @RequestBody PatchOperation[] patchOperations) {
        return getService().updateNote(noteId, patchOperations);
    }
}
