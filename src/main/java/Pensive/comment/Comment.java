package Pensive.comment;

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
@Document(collection = "comments")
public class Comment extends DomainObject {

    private int parentId;
    private String text;
    private Date lastEditDate;
    private Date creationDate;
    private BookInfo bookInfo;
    private UserInfo userInfo;
    private List<String> relatedTo;

    public Comment() {
        super();
    }
}
