package Pensive.author;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
    public Author findByLastName(String lastName);
}
