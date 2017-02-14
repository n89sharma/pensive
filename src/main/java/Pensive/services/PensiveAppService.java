package Pensive.services;

import Pensive.domainobjects.PatchOperation;
import lombok.Getter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

@Getter
public abstract class PensiveAppService<D, I extends Serializable, T extends MongoRepository<D, I>> {
    private T repository;

    public PensiveAppService(T repository) {
        this.repository = repository;
    }

    public D addDomainObject(D object) {
        return repository.save(object);
    }

    public List<D> getAllDomainObjects() {
        return repository.findAll();
    }

    public D getDomainObject(I id) {
        D repositoryObject = null;
        if (repository.exists(id)) {
            repositoryObject = repository.findOne(id);
        }
        return repositoryObject;
    }

    public void deleteDomainObject(I id) {
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
