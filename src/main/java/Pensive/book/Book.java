package Pensive.book;

import Pensive.prototype.DomainObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "books")
public class Book extends DomainObject {

    private String bookTitle;
    private String summary;
    private String publisher;
    private int isbn;
    private Map<String, List<String>> chapters;

    public Book() {
        super();
    }

    public Book(String bookTitle, String chapterTitle) {
        super();
        this.bookTitle = bookTitle;
        this.chapters = new HashMap<String, List<String>>();
        this.chapters.put(chapterTitle, new ArrayList<String>());
    }
}
