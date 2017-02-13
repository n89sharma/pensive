package Pensive.repositories;

import Pensive.domainobjects.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
    public Book findByBookTitle(String bookTitle);

    public void deleteByBookTitle(String bookTitle);
    public Book save(Book book);
}
