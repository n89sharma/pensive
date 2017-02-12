package Pensive.controllers;

import Pensive.domainobjects.Paragraph;
import Pensive.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 ENDPOINT                                                                 HTTP METHOD  DESCRIPTION                                ADDED TO THE CONTROLLER

 /books[?author=sagan]                                                    GET         get all the books with a specific criteria
 /books                                                                   POST        create a new book
 /books/{bookTitle}                                                       DELETE      delete a book
 /books/{bookTitle}/chapters                                              GET         get all the chapters in the book
 /books/{bookTitle}/chapters                                              POST        add a new chapter in the book
 /books/{bookTitle}/chapters/{chaterTitle}                                DELETE      delete a chapter from a book
 /books/{bookTitle}/chapters/{chaterTitle}/paragraphs                     GET         get all the paragraphs in a chapter
 /books/{bookTitle}/chapters/{chaterTitle}/paragraphs                     POST        add a new paragraph to a chapter            Y
 /books/{bookTitle}/chapters/{chaterTitle}/paragraphs/{paragraphId}       PUT         update a paragraph
 /books/{bookTitle}/chapters/{chaterTitle}/paragraphs/{paragraphId}       DELETE      delete a paragraph

 */

@RestController
@RequestMapping("/books")
public class BookController extends PensiveAppController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "{bookTitle}/chapters/{chapterTitle}/paragraphs", method = RequestMethod.POST)
    public Paragraph appendParagraphToChapter(
            @PathVariable String bookTitle,
            @PathVariable String chapterTitle,
            @RequestBody Paragraph paragraph) {
        return bookService.appendParagraphToChapter(bookTitle, chapterTitle, paragraph);
    }
}
