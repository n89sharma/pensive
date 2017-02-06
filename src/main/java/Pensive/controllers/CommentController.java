package Pensive.controllers;

import Pensive.domainobjects.Comment;
import Pensive.domainobjects.Comments;
import Pensive.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController extends PensiveAppController{

    //CRUD , create, read, update, delete

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
