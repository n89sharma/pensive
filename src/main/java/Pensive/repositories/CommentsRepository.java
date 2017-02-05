package Pensive.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import Pensive.domainobjects.Comment;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comment, String>{
    public List<Comment> findByParentId(String parentId);
    public Comment findOne(String id);
    public List<Comment> findAll();
    public Comment save(Comment comment);
}
