package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
public class Comment{
    @Id
    private String id;
    private String parentId;
    private String text;
    private int lastEditDate;
    private int creationDate;
    private BookAddress bookAddress;
    private UserInfo userInfo;
}
