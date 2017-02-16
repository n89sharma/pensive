package Pensive.book;

import Pensive.paragraph.Paragraph;
import Pensive.paragraph.ParagraphRepository;
import Pensive.prototype.PatchOperation;
import Pensive.prototype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class BookService extends Service<Book, BookValidator, BookRepository> {

    private ParagraphRepository paragraphRepository;

    @Autowired
    public BookService(
            ParagraphRepository paragraphRepository,
            BookRepository bookRepository,
            BookValidator bookValidator) {
        super(bookRepository, bookValidator);
        this.paragraphRepository = paragraphRepository;
    }

    public void deleteBook(String bookTitle) {
        if (null != getRepository().findByBookTitle(bookTitle)) {
            getRepository().deleteByBookTitle(bookTitle);
        }
    }

    public Map<String, List<String>> getChapters(String bookTitle) {
        Book book = getRepository().findByBookTitle(bookTitle);
        return book.getChapters();
    }

    public Map<String, List<String>> addChapters(String bookTitle, Map<String, List<String>> chapters) {
        Book book = getRepository().findByBookTitle(bookTitle);
        Map<String, List<String>> currentChapters = null == book.getChapters()
                ? new HashMap<String, List<String>>()
                : book.getChapters();
        for (String chapterTitle : chapters.keySet()) {
            if (!currentChapters.containsKey(chapterTitle)) {
                currentChapters.put(chapterTitle, chapters.get(chapterTitle));
            }
        }
        book.setChapters(currentChapters);
        getRepository().save(book);
        return currentChapters;
    }

    public Map<String, List<String>> deleteChapter(String bookTitle, String chapterTitle) {
        Book book = getRepository().findByBookTitle(bookTitle);
        Map<String, List<String>> currentChapters = book.getChapters();
        if (currentChapters.containsKey(chapterTitle)) {
            currentChapters.remove(chapterTitle);
            getRepository().save(book);
        }
        return book.getChapters();
    }

    public List<String> getParagraphs(String bookTitle, String chapterTitle) {
        Book book = getRepository().findByBookTitle(bookTitle);
        return book.getChapters().get(chapterTitle);
    }

    public Paragraph appendParagraphToChapter(String bookTitle, String chapterTitle, Paragraph paragraph) {
        Book book = getRepository().findByBookTitle(bookTitle);
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
        getRepository().save(book);
        paragraphRepository.save(paragraph);
    }

    private Book saveBook(String bookTitle, String chapterTitle) {
        Book book = new Book(bookTitle, chapterTitle);
        return addDomainObject(book);
    }

    private Paragraph saveParagraph(Paragraph paragraph) {
        return paragraphRepository.save(paragraph);
    }

    @Override
    protected void updateDomainObjectForPatchOperation(Book domainObject, PatchOperation patchOperation) {

    }
}
