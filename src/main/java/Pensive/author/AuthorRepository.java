package Pensive.author;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    public List<Author> findByLastName(String lastName);
}
