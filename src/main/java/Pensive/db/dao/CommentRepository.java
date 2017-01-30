package Pensive.db.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import Pensive.db.entity.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String>{
    public List<Comment> findByParentId(String parentId);
    public List<Comment> findAll();
}
