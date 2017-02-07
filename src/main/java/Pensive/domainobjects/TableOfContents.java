package Pensive.domainobjects;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TableOfContents {
    private Map<String, List<String>> tableOfContents;

    public TableOfContents() {
        tableOfContents = new LinkedHashMap<String, List<String>>();
    }
}
