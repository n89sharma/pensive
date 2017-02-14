package Pensive.controllers;

import Pensive.domainobjects.Note;
import Pensive.domainobjects.PatchOperation;
import Pensive.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class NoteController extends PensiveAppController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Note> getNotes() {
        return noteService.getAllDomainObjects();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Note addNote(@RequestBody Note note) {
        return noteService.addDomainObject(note);
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.GET)
    public void getNote(@PathVariable String noteId) {
        noteService.getDomainObject(noteId);
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable String noteId) {
        noteService.deleteDomainObject(noteId);
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.PATCH)
    public Note updateNote(@PathVariable String noteId, @RequestBody PatchOperation[] patchOperations) {
        return noteService.updateNote(noteId, patchOperations);
    }
}
