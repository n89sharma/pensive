package Pensive.prototype;

import lombok.Getter;
import org.springframework.data.mongodb.repository.MongoRepository;

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
        if (repository.exists(id)) {
            repository.delete(id);
        }
    }

    public boolean validatePatchOperation(PatchOperation patchOperation) {
        return null != patchOperation.getOp() &&
                null != patchOperation.getPath() &&
                null != patchOperation.getValue() &&
                !patchOperation.getOp().isEmpty() &&
                !patchOperation.getValue().isEmpty() &&
                !patchOperation.getPath().isEmpty();
    }
}
