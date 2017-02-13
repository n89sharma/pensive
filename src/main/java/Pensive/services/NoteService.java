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

@Service
public class NoteService extends PensiveAppService {

    private final static String pathOfParagraphId = "/paragraphIds/-";
    private final static String pathOfText = "/text";
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    public void deleteNote(String noteId) {
        if (null != noteRepository.findOne(noteId)) {
            noteRepository.delete(noteId);
        }
    }

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note updateNote(String noteId, PatchOperation[] patchOperations) {
        Note note = noteRepository.findOne(noteId);
        List<PatchOperation> patchOperationList = Arrays.asList(patchOperations);
        if (!patchOperationList.isEmpty()) {
            for (PatchOperation patchOperation : patchOperations) {
                if (validatePatchOperation(patchOperation)) {
                    if (patchOperation.getPath().equals(pathOfParagraphId)) {
                        updateParagraphId(patchOperation, note);
                    } else if (patchOperation.getPath().equals(pathOfText)) {
                        updateText(patchOperation, note);
                    }
                }
            }
        }
        return noteRepository.save(note);
    }

    private void updateParagraphId(PatchOperation patchOperation, Note note) {
        if (validatePatchOperationForUpdatingParagraphIds(patchOperation)) {
            List<String> paragraphId = null == note.getParagraphIds() ? new ArrayList<String>() : note.getParagraphIds();
            if (patchOperation.getOp().equals(PatchActionType.ADD.getActionKey()) &&
                    !paragraphId.contains(patchOperation.getValue())) {
                paragraphId.add(patchOperation.getValue());
            } else if (patchOperation.getOp().equals(PatchActionType.REMOVE.getActionKey()) &&
                    paragraphId.contains(patchOperation.getValue())) {
                paragraphId.remove(patchOperation.getValue());
            }
            note.setParagraphIds(paragraphId);
        }
    }

    private void updateText(PatchOperation patchOperation, Note note) {
        if (validatePatchOperationForNotes(patchOperation)
                && patchOperation.getOp().equals(PatchActionType.REPLACE.getActionKey())) {
            note.setText(patchOperation.getValue());
        }
    }

    private boolean validatePatchOperationForUpdatingParagraphIds(PatchOperation patchOperation) {
        boolean isOperationValid = patchOperation.getOp().equals(PatchActionType.ADD.getActionKey()) ||
                patchOperation.getOp().equals(PatchActionType.REMOVE.getActionKey());
        boolean isPathValid = patchOperation.getPath().equals(pathOfParagraphId);
        boolean isValueValid = ObjectId.isValid(patchOperation.getValue());
        return isOperationValid && isPathValid && isValueValid;
    }

    private boolean validatePatchOperationForNotes(PatchOperation patchOperation) {
        boolean isOperationValid = patchOperation.getOp().equals(PatchActionType.REPLACE.getActionKey());
        boolean isPathValid = patchOperation.getPath().equals(pathOfText);
        return isOperationValid && isPathValid;
    }
}
