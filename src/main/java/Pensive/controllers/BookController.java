package Pensive.controllers;

import Pensive.domainobjects.Paragraph;
import Pensive.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ENDPOINT                                               HTTP METHOD  DESCRIPTION                                    ADDED TO THE CONTROLLER
 * <p>
 * /books[?author=sagan]                                   GET         get all the books with a specific criteria
 * /books                                                  POST        create a new book
 * /books/cosmos                                           DELETE      delete a book
 * /books/cosmos/chapters                                  GET         get all the chapters in the book
 * /books/cosmos/chapters                                  POST        add a new chapter in the book
 * /books/cosmos/chapters/intro                            DELETE      delete a chapter from a book
 * /books/cosmos/chapters/intro/paragraphs                 GET         get all the paragraphs in a chapter
 * /books/cosmos/chapters/intro/paragraphs                 POST        add a new paragraph to a chapter               Y
 * /books/cosmos/chapters/intro/paragraphs/paragraphId     PUT         update a paragraph
 * /books/cosmos/chapters/intro/paragraphs/paragraphId     DELETE      delete a paragraph
 */
@RestController
public class BookController extends PensiveAppController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books/{bookTitle}/chapters/{chapterTitle}/paragraphs", method = RequestMethod.POST)
    public Paragraph appendParagraphToChapter(
            @PathVariable(value = "bookTitle") String bookTitle,
            @PathVariable(value = "chapterTitle") String chapterTitle,
            @RequestBody Paragraph paragraph) {
        return bookService.appendParagraphToChapter(bookTitle, chapterTitle, paragraph);
    }

}
