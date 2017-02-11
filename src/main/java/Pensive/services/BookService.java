package Pensive.services;

import Pensive.domainobjects.Book;
import Pensive.domainobjects.BookInfo;
import Pensive.domainobjects.Paragraph;
import Pensive.repositories.BookRepository;
import Pensive.repositories.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
