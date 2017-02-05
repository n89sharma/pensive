package Pensive.repositories;

import Pensive.domainobjects.Paragraph;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParagraphsRepository extends MongoRepository<Paragraph, String> {
    public List<Paragraph> findByBook(String book);
    public Paragraph findOne(String id);
    public List<Paragraph> findAll();
    public Paragraph save(Paragraph paragraph);
}
