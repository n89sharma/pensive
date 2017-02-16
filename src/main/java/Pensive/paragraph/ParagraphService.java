package Pensive.paragraph;

import Pensive.prototype.PatchActionType;
import Pensive.prototype.PatchOperation;
import Pensive.prototype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ParagraphService extends Service<Paragraph, ParagraphValidator, ParagraphRepository> {

    private final static String pathOfText = "/text";

    @Autowired
    public ParagraphService(ParagraphRepository paragraphRepository, ParagraphValidator paragraphValidator) {
        super(paragraphRepository, paragraphValidator);
    }

    @Override
    protected void updateDomainObjectForPatchOperation(Paragraph paragraph, PatchOperation patchOperation) {
        if (getValidator().validatePatchOperationForDomainObject(
                pathOfText,
                patchOperation,
                PatchActionType.REPLACE)) {
            paragraph.setText(patchOperation.getValue());
        }
    }

}
