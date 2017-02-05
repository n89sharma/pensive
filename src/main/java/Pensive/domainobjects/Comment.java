package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "comments")
public class Comment{
    @Id
    private String id;
    private String parentId;
    private String text;
}
