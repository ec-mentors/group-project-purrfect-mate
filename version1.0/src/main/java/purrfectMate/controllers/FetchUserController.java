package purrfectMate.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import purrfectMate.Security.UserPrincipal;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class FetchUserController {

    @GetMapping("/me")
    public UserPrincipal getCurrentUser(Principal principal) {
        return (UserPrincipal) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }
}
