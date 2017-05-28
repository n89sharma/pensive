package Pensive.author;

import Pensive.prototype.DomainObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "authors")
public class Author extends DomainObject {

    private String lastName;
    private String firstName;
    private String careerDetails;
    private String educationBackground;
    private List<String> bookIds;

    public String getFullName() {
        return new StringBuilder()
                .append(firstName)
                .append(" ")
                .append(lastName)
                .toString();
    }

    public Author() {
        super();
    }

    public Author(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
