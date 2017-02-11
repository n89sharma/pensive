package Pensive.domainobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {
    private String bookId;
    private String authorFirstName;
    private String authorLastName;
    private String bookTitle;
    private String chapterTitle;

    public BookInfo(String bookId, String bookTitle, String chapterTitle) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.chapterTitle = chapterTitle;
    }
}
