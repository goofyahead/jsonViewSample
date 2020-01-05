package ninja.goofyahead;

import com.fasterxml.jackson.annotation.JsonView;
import ninja.goofyahead.model.User;
import ninja.goofyahead.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    @JsonView(View.Public.class)
    public User getUser(@PathVariable int id) {
        return new User(3, "Alex", "Vidal", "TribalScale");
    }

    @PostMapping
    public User saveUser(@JsonView(View.Public.class) @RequestBody User user){
        log.debug(user.toString());
        return user;
    }
}
