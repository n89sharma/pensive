package Pensive.book;

import Pensive.paragraph.Paragraph;
import Pensive.prototype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 ENDPOINT                                                                 HTTP METHOD  DESCRIPTION                                ADDED TO THE CONTROLLER

 /books[?author=sagan]                                                    GET         get all the books with a specific criteria    Y
 /books                                                                   POST        create a new book                             Y
 /books/{bookTitle}                                                       DELETE      delete a book                                 Y
 /books/{bookTitle}/chapters                                              GET         get all the chapters in the book              Y
 /books/{bookTitle}/chapters                                              POST        add a new chapter in the book                 Y
 /books/{bookTitle}/chapters/{chaterTitle}                                DELETE      delete a chapter from a book                  Y
 /books/{bookTitle}/chapters/{chaterTitle}/paragraphs                     GET         get all the paragraphs in a chapter           Y
 /books/{bookTitle}/chapters/{chaterTitle}/paragraphs                     POST        add a new paragraph to a chapter              Y
 */

@RestController
@RequestMapping("/books")
public class BookController extends Controller<Book, BookValidator, BookRepository, BookService> {

    @Autowired
    public BookController(BookService bookService) {
        super(bookService);
    }

    @RequestMapping(value = "/{bookTitle}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String bookTitle) {
        getService().deleteBook(bookTitle);
    }

    @RequestMapping(value = "/{bookTitle}/chapters", method = RequestMethod.GET)
    public Map<String, List<String>> getChapters(@PathVariable String bookTitle) {
        return getService().getChapters(bookTitle);
    }

    @RequestMapping(value = "/{bookTitle}/chapters", method = RequestMethod.POST)
    public Map<String, List<String>> addChapters(
            @PathVariable String bookTitle,
            @RequestBody Map<String, List<String>> chapters) {
        return getService().addChapters(bookTitle, chapters);
    }

    @RequestMapping(value = "/{bookTitle}/chapters/{chapterTitle}", method = RequestMethod.DELETE)
    public Map<String, List<String>> deleteChapter(@PathVariable String bookTitle, @PathVariable String chapterTitle) {
        return getService().deleteChapter(bookTitle, chapterTitle);
    }

    @RequestMapping(value = "/{bookTitle}/chapters/{chapterTitle}/paragraphs", method = RequestMethod.GET)
    public List<String> getParagraphs(@PathVariable String bookTitle, @PathVariable String chapterTitle) {
        return getService().getParagraphs(bookTitle, chapterTitle);
    }

    @RequestMapping(value = "{bookTitle}/chapters/{chapterTitle}/paragraphs", method = RequestMethod.POST)
    public Paragraph appendParagraphToChapter(
            @PathVariable String bookTitle,
            @PathVariable String chapterTitle,
            @RequestBody Paragraph paragraph) {
        return getService().appendParagraphToChapter(bookTitle, chapterTitle, paragraph);
    }
}
