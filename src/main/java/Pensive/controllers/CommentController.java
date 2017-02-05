package Pensive.controllers;

import Pensive.domainobjects.Comment;
import Pensive.repositories.CommentsRepository;
import Pensive.domainobjects.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController extends PensiveAppController{

    @Autowired
    private CommentsRepository commentsRepository;

    @RequestMapping(value="/comments/{parentid}", method = RequestMethod.GET)
    public Comments getComments(@PathVariable(value = "parentid") String parentId){
        return new Comments(parentId, commentsRepository.findByParentId(parentId));
    }

    @RequestMapping(value="/comments", method=RequestMethod.POST)
    public Comment addComment(@RequestBody Comment comment){
        return commentsRepository.save(comment);
    }
}
