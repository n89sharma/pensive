package Pensive.services;

import Pensive.domainobjects.Note;
import Pensive.domainobjects.PatchActionType;
import Pensive.domainobjects.PatchOperation;
import Pensive.repositories.NoteRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ENDPOINT                                               HTTP METHOD  DESCRIPTION
 * <p>
 * /notes[?users=sharmani]                                 GET         get all the notes with a specific criteria
 * /notes                                                  POST        create a note
 * /notes/noteId                                           PATCH       update the paragraphID list in the note
 * /notes/noteId                                           PATCH       update the text in the note
 * /notes/noteId                                           DELETE      delete a note
 */

@Service
public class NoteService extends PensiveAppService {

    private final static String pathOfParagraphId = "/paragraphIds/-";
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note updateParagraphIdList(String noteId, PatchOperation[] patchOperations) {
        Note note = noteRepository.findOne(noteId);
        List<String> paragraphId = note.getParagraphIds();
        if (null == paragraphId) {
            paragraphId = new ArrayList<String>();
        }
        List<PatchOperation> patchOperationList = Arrays.asList(patchOperations);
        if (!patchOperationList.isEmpty()) {
            for (PatchOperation patchOperation : patchOperations) {
                if (validatePatchOperation(patchOperation) && validateUpdateParagraphOperation(patchOperation)) {
                    if (patchOperation.getOp().equals(PatchActionType.ADD.getActionKey()) &&
                            !paragraphId.contains(patchOperation.getValue())) {
                        paragraphId.add(patchOperation.getValue());
                    } else if (patchOperation.getOp().equals(PatchActionType.REMOVE.getActionKey()) &&
                            paragraphId.contains(patchOperation.getValue())) {
                        paragraphId.remove(patchOperation.getValue());
                    }
                }
            }
        }
        note.setParagraphIds(paragraphId);
        return noteRepository.save(note);
    }

    private boolean validateUpdateParagraphOperation(PatchOperation patchOperation) {
        boolean isOperationValid = patchOperation.getOp().equals(PatchActionType.ADD.getActionKey()) ||
                patchOperation.getOp().equals(PatchActionType.REMOVE.getActionKey());
        boolean isPathValid = patchOperation.getPath().equals(pathOfParagraphId);
        boolean isValueValid = ObjectId.isValid(patchOperation.getValue());
        return isOperationValid && isPathValid && isValueValid;
    }
}
