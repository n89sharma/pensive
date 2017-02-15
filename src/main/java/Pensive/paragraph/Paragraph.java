package Pensive.paragraph;

import Pensive.book.BookInfo;
import Pensive.prototype.DomainObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "paragraphs")
public class Paragraph extends DomainObject {

    private String text;
    private BookInfo bookInfo;

    public Paragraph() {
        super();
    }
}
