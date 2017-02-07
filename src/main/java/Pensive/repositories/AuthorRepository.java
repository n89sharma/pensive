package Pensive.repositories;

import Pensive.domainobjects.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
    public Author findByLastName(String lastName);
}
