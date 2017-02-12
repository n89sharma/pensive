package Pensive.domainobjects;

import lombok.Data;

@Data
public class PatchOperation {
    private String op;
    private String path;
    private String value;
}
