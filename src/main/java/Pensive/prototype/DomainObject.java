package Pensive.prototype;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class DomainObject {

    private String id;
    private List<String> errors;

    public DomainObject() {
        this.errors = new ArrayList<String>();
    }

    public List<String> addError(String error) {
        errors.add(error);
        return errors;
    }

}
