package Pensive.repositories;

import Pensive.domainobjects.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {
    public Note findOne(String id);
    public List<Note> findAll();
    public Note save(Note note);
}
