package Pensive.prototype;

import org.bson.types.ObjectId;

import java.util.Arrays;

public abstract class Validator<D extends DomainObject> {

    public boolean isValidForAdd(D domainObject) {
        return true;
    }

    public boolean isValidForRead(String domainObjectId) {
        return hasValidId(domainObjectId);
    }

    public boolean isValidForUpdate(String domainObjectId) {
        return hasValidId(domainObjectId);
    }

    public boolean isValidForDelete(String domainObjectId) {
        return hasValidId(domainObjectId);
    }

    public boolean hasValidId(String id) {
        return ObjectId.isValid(id);
    }

    public boolean isPatchOperationValid(PatchOperation patchOperation) {
        return null != patchOperation.getOp() &&
                null != patchOperation.getPath() &&
                null != patchOperation.getValue() &&
                !patchOperation.getOp().isEmpty() &&
                !patchOperation.getValue().isEmpty() &&
                !patchOperation.getPath().isEmpty();
    }

    public boolean validatePatchOperationForDomainObject(
            String validPath,
            PatchOperation patchOperation,
            PatchActionType... validActionTypes) {

        boolean isOperationValid = false;
        for (PatchActionType validAction : Arrays.asList(validActionTypes)) {
            isOperationValid = isOperationValid || patchOperation.getOp().equals(validAction.getActionKey());
        }
        boolean isPathValid = patchOperation.getPath().equals(validPath);
        return isOperationValid && isPathValid;
    }

}
