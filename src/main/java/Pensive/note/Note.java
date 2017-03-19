package Pensive.note;

import Pensive.book.BookInfo;
import Pensive.prototype.DomainObject;
import Pensive.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "notes")
public class Note extends DomainObject {

    private String quoteText;
    private String noteTitle;
    private String text;
    private boolean locked;
    private Date creationDate;
    private Date lastEditDate;
    private NoteType typeOfNote;
    private UserInfo userInfo;
    private BookInfo bookInfo;
    private List<String> paragraphIds;

    public Note() {
        super();
    }
}
