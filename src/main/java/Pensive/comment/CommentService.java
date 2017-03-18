package Pensive.comment;

import Pensive.prototype.PatchActionType;
import Pensive.prototype.PatchOperation;
import Pensive.prototype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class CommentService extends Service<Comment, CommentValidator, CommentRepository> {

    private final static String pathOfRelatedTo = "/relatedTo/-";

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentValidator commentValidator) {
        super(commentRepository, commentValidator);
    }

    @Override
    protected void updateDomainObjectForPatchOperation(Comment comment, PatchOperation patchOperation) {
        if (getValidator().validatePatchOperationForDomainObject(
                pathOfRelatedTo,
                patchOperation,
                PatchActionType.ADD,
                PatchActionType.REMOVE)) {
            List<String> relatedTo = null == comment.getRelatedTo() ? new ArrayList<String>() : comment.getRelatedTo();
            updateListFromPatchOperation(relatedTo, patchOperation);
            comment.setRelatedTo(relatedTo);
        }
    }

}
