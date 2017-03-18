package Pensive.comment;

import Pensive.prototype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController extends Controller<Comment, CommentValidator, CommentRepository, CommentService> {

    @Autowired
    public CommentController(CommentService commentService) {
        super(commentService);
    }
}
