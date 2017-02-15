package Pensive.author;

import Pensive.book.Book;
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
    private List<Book> publications;

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
}
