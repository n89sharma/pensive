package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {
    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String profession;
    private String careerDetails;
    private String education;
    private List<Book> publications;

    public String getFullName() {
        return new StringBuilder()
                .append(firstName)
                .append(" ")
                .append(lastName)
                .toString();
    }
}
