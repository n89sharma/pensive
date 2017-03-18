package Pensive.prototype;

import lombok.Getter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Service<
        D extends DomainObject,
        V extends Validator<D>,
        T extends MongoRepository<D, String>> {

    private T repository;
    private V validator;

    public Service(T repository, V validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public D addDomainObject(D object) {
        D repositoryObject = null;
        if (validator.isValidForAdd(object)) {
            repositoryObject = repository.save(object);
        }
        return repositoryObject;
    }

    public List<D> getAllDomainObjects() {
        return repository.findAll();
    }

    public D readDomainObject(String id) {
        D repositoryObject = null;
        if (validator.isValidForRead(id) && repository.exists(id)) {
            repositoryObject = repository.findOne(id);
        }
        return repositoryObject;
    }

    public void deleteDomainObject(String id) {
        if (validator.isValidForDelete(id) && repository.exists(id)) {
            repository.delete(id);
        }
    }

    public D updateDomainObject(String id, PatchOperation[] patchOperations) {
        D domainObject = repository.findOne(id);
        List<PatchOperation> patchOperationList = Arrays.asList(patchOperations);
        if (!patchOperationList.isEmpty() && null != domainObject) {
            for (PatchOperation patchOperation : patchOperations) {
                if (validator.isPatchOperationValid(patchOperation)) {
                    updateDomainObjectForPatchOperation(domainObject, patchOperation);
                }
            }
        }
        if (domainObject.getErrors().isEmpty()) {
            repository.save(domainObject);
        }
        return domainObject;
    }

    protected abstract void updateDomainObjectForPatchOperation(D domainObject, PatchOperation patchOperation);

    protected void updateListFromPatchOperation(List<String> ids, PatchOperation patchOperation) {
        if (patchOperation.getOp().equals(PatchActionType.ADD.getActionKey()) &&
                !ids.contains(patchOperation.getValue())) {
            ids.add(patchOperation.getValue());
        } else if (patchOperation.getOp().equals(PatchActionType.REMOVE.getActionKey()) &&
                ids.contains(patchOperation.getValue())) {
            ids.remove(patchOperation.getValue());
        }
    }
}
