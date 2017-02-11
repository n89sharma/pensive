package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notes")
public class Note{
    //TODO change type of note to enum
    //TODO change int to Date type for date variables
    //TODO should we use int for dates? if I use java date should I have another class that maps this class?
    //TODO both note and paragraph have book chapter section to avoid mutiple joins - check if this makes sense.
    @Id
    private String id;
    private String quoteText;
    private String noteTitle;
    private String text;
    private boolean lockStatus;
    private int creationDate;
    private int lastEditDate;
    private NoteType typeOfNote;
    private UserInfo userInfo;
    private BookInfo bookInfo;
    private List<String> paragraphIds;
}
