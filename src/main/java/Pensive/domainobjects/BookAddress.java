package Pensive.domainobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookAddress {
    private String bookId;
    private String authorFullName;
    private String title;
    private String chapter;
    private String section;
}
