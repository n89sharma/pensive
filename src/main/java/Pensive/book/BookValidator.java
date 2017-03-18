package Pensive.book;

import Pensive.prototype.Validator;
import org.springframework.stereotype.Component;

@Component
public class BookValidator extends Validator<Book> {

    @Override
    public boolean isValidForAdd(Book book) {
        return null != book &&
                hasValidId(book.getId()) &&
                null != book.getBookTitle() &&
                !book.getBookTitle().isEmpty();
    }
}
