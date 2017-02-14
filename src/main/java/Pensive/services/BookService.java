package Pensive.services;

import Pensive.domainobjects.Book;
import Pensive.domainobjects.BookInfo;
import Pensive.domainobjects.Paragraph;
import Pensive.repositories.BookRepository;
import Pensive.repositories.ParagraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService extends PensiveAppService<Book, String, BookRepository> {

    private BookRepository bookRepository;
    private ParagraphRepository paragraphRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ParagraphRepository paragraphRepository) {
        super(bookRepository);
        this.paragraphRepository = paragraphRepository;
    }

    public void deleteBook(String bookTitle) {
        if (null != bookRepository.findByBookTitle(bookTitle)) {
            bookRepository.deleteByBookTitle(bookTitle);
        }
    }

    public Map<String, List<String>> getChapters(String bookTitle) {
        Book book = bookRepository.findByBookTitle(bookTitle);
        return book.getChapters();
    }

    public Map<String, List<String>> addChapters(String bookTitle, Map<String, List<String>> chapters) {
        Book book = bookRepository.findByBookTitle(bookTitle);
        Map<String, List<String>> currentChapters = null == book.getChapters()
                ? new HashMap<String, List<String>>()
                : book.getChapters();
        for (String chapterTitle : chapters.keySet()) {
            if (!currentChapters.containsKey(chapterTitle)) {
                currentChapters.put(chapterTitle, chapters.get(chapterTitle));
            }
        }
        book.setChapters(currentChapters);
        bookRepository.save(book);
        return currentChapters;
    }

    public Map<String, List<String>> deleteChapter(String bookTitle, String chapterTitle) {
        Book book = bookRepository.findByBookTitle(bookTitle);
        Map<String, List<String>> currentChapters = book.getChapters();
        if (currentChapters.containsKey(chapterTitle)) {
            currentChapters.remove(chapterTitle);
            bookRepository.save(book);
        }
        return book.getChapters();
    }

    public List<String> getParagraphs(String bookTitle, String chapterTitle) {
        Book book = bookRepository.findByBookTitle(bookTitle);
        return book.getChapters().get(chapterTitle);
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
