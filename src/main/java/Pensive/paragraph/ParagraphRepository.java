package Pensive.paragraph;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParagraphRepository extends MongoRepository<Paragraph, String> {
    public Paragraph findOne(String id);
    public List<Paragraph> findAll();
    public Paragraph save(Paragraph paragraph);
}
