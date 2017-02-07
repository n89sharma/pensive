package Pensive.services;

import Pensive.domainobjects.Comment;
import Pensive.domainobjects.aggregated.Comments;
import Pensive.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentService extends PensiveAppService {

    @Autowired
    CommentRepository commentRepository;

    public Comments getComments(String parentId) {
        return new Comments(parentId, commentRepository.findByParentId(parentId));
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
