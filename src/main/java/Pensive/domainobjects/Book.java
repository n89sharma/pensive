package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id;
    private String bookTitle;
    private String summary;
    private String publisher;
    private int isbn;
    private Map<String, List<String>> chapters;

    public Book(String bookTitle, String chapterTitle) {
        this.bookTitle = bookTitle;
        this.chapters = new HashMap<String, List<String>>();
        this.chapters.put(chapterTitle, new ArrayList<String>());
    }
}
