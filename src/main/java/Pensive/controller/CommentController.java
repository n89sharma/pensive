package Pensive.controller;

import Pensive.db.dao.CommentRepository;
import Pensive.domain.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController extends PensiveAppController{

    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(value="/comments/{parentid}", method = RequestMethod.GET)
    public Comments getComments(@PathVariable(value = "parentid") String parentId){
        return new Comments(parentId, commentRepository.findByParentId(parentId));
    }
}
