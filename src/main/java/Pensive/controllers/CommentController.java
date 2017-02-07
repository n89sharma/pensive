package Pensive.controllers;

import Pensive.domainobjects.Comment;
import Pensive.domainobjects.aggregated.Comments;
import Pensive.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController extends PensiveAppController{

    @Autowired
    private CommentService commentService;

    @RequestMapping(value="/comments/{parentid}", method = RequestMethod.GET)
    public Comments getComments(@PathVariable(value = "parentid") String parentId){
        return commentService.getComments(parentId);
    }

    @RequestMapping(value="/comments", method=RequestMethod.POST)
    public Comment addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
