package purrfectMate.controllers.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import purrfectMate.data.entity.User;
import purrfectMate.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminEndpointSecured {

    private final UserService userService;

    public AdminEndpointSecured(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
