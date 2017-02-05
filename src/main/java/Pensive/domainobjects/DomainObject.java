package Pensive.domainobjects;

import lombok.Data;

import java.util.List;

@Data
public class DomainObject {
    private List<String> errors;
    //TODO connect other objects to this main domain object.
    //TODO this is one of the reasons database objects and domain objects are kept seperate ?
}
