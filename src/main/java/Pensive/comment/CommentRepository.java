package Pensive.comment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    public List<Comment> findByParentId(String parentId);
    public Comment findOne(String id);
    public Comment save(Comment comment);
}
