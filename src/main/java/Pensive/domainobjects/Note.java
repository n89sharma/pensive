package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notes")
public class Note{
    @Id
    private String id;
    private String quoteText;
    private String noteTitle;
    private String text;
    private boolean lockStatus;
    private Date creationDate;
    private Date lastEditDate;
    private NoteType typeOfNote;
    private UserInfo userInfo;
    private BookInfo bookInfo;
    private List<String> paragraphIds;
}
