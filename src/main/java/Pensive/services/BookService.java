package Pensive.services;

import Pensive.domainobjects.Book;
import Pensive.domainobjects.BookInfo;
import Pensive.domainobjects.Paragraph;
import Pensive.repositories.BookRepository;
import Pensive.repositories.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ENDPOINT                                                                 HTTP METHOD  DESCRIPTION                                ADDED TO THE CONTROLLER
 * <p>
 * /books[?author=sagan]                                                    GET         get all the books with a specific criteria
 * /books                                                                   POST        create a new book
 * /books/{bookTitle}                                                       DELETE      delete a book
 * /books/{bookTitle}/chapters                                              GET         get all the chapters in the book
 * /books/{bookTitle}/chapters                                              POST        add a new chapter in the book
 * /books/{bookTitle}/chapters/{chaterTitle}                                DELETE      delete a chapter from a book
 * /books/{bookTitle}/chapters/{chaterTitle}/paragraphs                     GET         get all the paragraphs in a chapter
 * /books/{bookTitle}/chapters/{chaterTitle}/paragraphs                     POST        add a new paragraph to a chapter            Y
 * /books/{bookTitle}/chapters/{chaterTitle}/paragraphs/{paragraphId}       PUT         update a paragraph
 * /books/{bookTitle}/chapters/{chaterTitle}/paragraphs/{paragraphId}       DELETE      delete a paragraph
 */

@Service
public class BookService {

    private BookRepository bookRepository;
    private ParagraphRepository paragraphRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ParagraphRepository paragraphRepository) {
        this.bookRepository = bookRepository;
        this.paragraphRepository = paragraphRepository;
    }

    public Paragraph appendParagraphToChapter(String bookTitle, String chapterTitle, Paragraph paragraph) {
        Book book = bookRepository.findByBookTitle(bookTitle);
        if (null == book) {
            book = saveBook(bookTitle, chapterTitle);
        }
        Paragraph savedParagraph = saveParagraph(paragraph);
        addParagraphToChapter(book, chapterTitle, savedParagraph);
        return savedParagraph;
    }

    public void addParagraphToChapter(Book book, String chapterTitle, Paragraph paragraph) {
        List<String> paragraphList = book.getChapters().get(chapterTitle);
        if (null != paragraphList) {
            paragraphList.add(paragraph.getId());
        }
        book.getChapters().put(chapterTitle, paragraphList);
        paragraph.setBookInfo(new BookInfo(book.getId(), book.getBookTitle(), chapterTitle));
        bookRepository.save(book);
        paragraphRepository.save(paragraph);
    }

    private Book saveBook(String bookTitle, String chapterTitle) {
        Book book = new Book(bookTitle, chapterTitle);
        return bookRepository.save(book);
    }

    private Paragraph saveParagraph(Paragraph paragraph) {
        return paragraphRepository.save(paragraph);
    }
}
