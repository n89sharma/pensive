package Pensive.prototype;

import lombok.Getter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(produces = APPLICATION_JSON_VALUE)
public abstract class Controller<
        D extends DomainObject,
        V extends Validator<D>,
        T extends MongoRepository<D, String>,
        S extends Service<D, V, T>> {

    @Getter
    private S service;

    public Controller(S service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<D> getAllDomainObjects() {
        return service.getAllDomainObjects();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public D addNote(@RequestBody D domainObject) {
        return service.addDomainObject(domainObject);
    }

    @RequestMapping(value = "/{domainObjectId}", method = RequestMethod.GET)
    public D getDomainObject(@PathVariable String domainObjectId) {
        return service.readDomainObject(domainObjectId);
    }

    @RequestMapping(value = "/{domainObjectId}", method = RequestMethod.DELETE)
    public void deleteDomainObject(@PathVariable String domainObjectId) {
        service.deleteDomainObject(domainObjectId);
    }

    @RequestMapping(value = "/{domainObjectId}", method = RequestMethod.PATCH)
    public D updateDomainObject(@PathVariable String domainObjectId, @RequestBody PatchOperation[] patchOperations) {
        return service.updateDomainObject(domainObjectId, patchOperations);
    }
}
