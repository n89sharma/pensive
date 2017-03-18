package Pensive.note;

import Pensive.prototype.PatchActionType;
import Pensive.prototype.PatchOperation;
import Pensive.prototype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class NoteService extends Service<Note, NoteValidator, NoteRepository> {

    private final static String pathOfParagraphId = "/paragraphIds/-";
    private final static String pathOfText = "/text";

    @Autowired
    public NoteService(NoteRepository noteRepository, NoteValidator noteValidator) {
        super(noteRepository, noteValidator);
    }

    @Override
    protected void updateDomainObjectForPatchOperation(Note note, PatchOperation patchOperation) {
        if (patchOperation.getPath().equals(pathOfParagraphId)) {
            updateParagraphId(patchOperation, note);
        } else if (patchOperation.getPath().equals(pathOfText)) {
            updateText(patchOperation, note);
        }
    }

    private void updateParagraphId(PatchOperation patchOperation, Note note) {
        if (getValidator().validatePatchOperationForDomainObject(
                pathOfParagraphId,
                patchOperation,
                PatchActionType.ADD,
                PatchActionType.REMOVE)) {
            List<String> paragraphId = null == note.getParagraphIds() ? new ArrayList<String>() : note.getParagraphIds();
            updateListFromPatchOperation(paragraphId, patchOperation);
            note.setParagraphIds(paragraphId);
        }
    }

    private void updateText(PatchOperation patchOperation, Note note) {
        if (getValidator().validatePatchOperationForDomainObject(pathOfText, patchOperation, PatchActionType.REPLACE)) {
            note.setText(patchOperation.getValue());
        }
    }
}
