package Pensive.user;

import Pensive.prototype.DomainObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")
public class User extends DomainObject {

    private String username;
    private String legalName;
    private List<String> bookIds;

    public User() {
        super();
    }
}
