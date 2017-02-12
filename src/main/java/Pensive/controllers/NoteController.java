package Pensive.controllers;

import Pensive.domainobjects.Note;
import Pensive.domainobjects.PatchOperation;
import Pensive.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ENDPOINT                                               HTTP METHOD  DESCRIPTION
 * <p>
 * /notes[?users=sharmani]                                 GET         get all the notes with a specific criteria
 * /notes                                                  POST        create a note
 * /notes/noteId                                           DELETE      delete a note
 * /notes/noteId                                           PATCH       update the text in a note
 * /notes/noteId                                           PATCH       update the paragraph id's in a note
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
        return noteService.getNotes();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }

    @RequestMapping(value = "/{noteId}", method = RequestMethod.PATCH)
    public Note updateParagraphIdList(@PathVariable String noteId, @RequestBody PatchOperation[] patchOperations) {
        return noteService.updateParagraphIdList(noteId, patchOperations);
    }
}
