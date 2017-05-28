package Pensive.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {
    private int bookId;
    private String authorFirstName;
    private String authorLastName;
    private String bookTitle;
    private String chapterTitle;

    public BookInfo(int bookId, String bookTitle, String chapterTitle) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.chapterTitle = chapterTitle;
    }
}
