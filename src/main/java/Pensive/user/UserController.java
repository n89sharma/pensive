package Pensive.user;

import Pensive.prototype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends Controller<User, UserValidator, UserRepository, UserService> {

    @Autowired
    public UserController(UserService userService) {
        super(userService);
    }
}
