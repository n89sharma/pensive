package Pensive.author;

import Pensive.prototype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController extends Controller<Author, AuthorValidator, AuthorRepository, AuthorService> {

    @Autowired
    public AuthorController(AuthorService authorService) {
        super(authorService);
    }

    @RequestMapping("/{lastName}")
    public Author deleteAuthor(@PathVariable String lastName) {
        return getService().deleteAuthor(lastName);
    }
}
