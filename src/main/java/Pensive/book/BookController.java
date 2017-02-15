package Pensive.book;

import Pensive.paragraph.Paragraph;
import Pensive.prototype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

@RequestMapping("/books")
public class BookController extends Controller {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Book> getBooks() {
        return bookService.getAllDomainObjects();
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public Book getBook(@PathVariable String bookId) {
        return bookService.readDomainObject(bookId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book) {
        return bookService.addDomainObject(book);
    }

    @RequestMapping(value = "/{bookTitle}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String bookTitle) {
        bookService.deleteBook(bookTitle);
    }

    @RequestMapping(value = "/{bookTitle}/chapters", method = RequestMethod.GET)
    public Map<String, List<String>> getChapters(@PathVariable String bookTitle) {
        return bookService.getChapters(bookTitle);
    }

    @RequestMapping(value = "/{bookTitle}/chapters", method = RequestMethod.POST)
    public Map<String, List<String>> addChapters(
            @PathVariable String bookTitle,
            @RequestBody Map<String, List<String>> chapters) {
        return bookService.addChapters(bookTitle, chapters);
    }

    @RequestMapping(value = "/{bookTitle}/chapters/{chapterTitle}", method = RequestMethod.DELETE)
    public Map<String, List<String>> deleteChapter(@PathVariable String bookTitle, @PathVariable String chapterTitle) {
        return bookService.deleteChapter(bookTitle, chapterTitle);
    }

    @RequestMapping(value = "/{bookTitle}/chapters/{chapterTitle}/paragraphs", method = RequestMethod.GET)
    public List<String> getParagraphs(@PathVariable String bookTitle, @PathVariable String chapterTitle) {
        return bookService.getParagraphs(bookTitle, chapterTitle);
    }

    @RequestMapping(value = "{bookTitle}/chapters/{chapterTitle}/paragraphs", method = RequestMethod.POST)
    public Paragraph appendParagraphToChapter(
            @PathVariable String bookTitle,
            @PathVariable String chapterTitle,
            @RequestBody Paragraph paragraph) {
        return bookService.appendParagraphToChapter(bookTitle, chapterTitle, paragraph);
    }
}
