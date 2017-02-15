package Pensive.prototype;

import org.bson.types.ObjectId;

public abstract class Validator<D extends DomainObject> {

    public boolean isValidForAdd(D domainObject) {
        return hasValidId(domainObject.getId());
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
}
