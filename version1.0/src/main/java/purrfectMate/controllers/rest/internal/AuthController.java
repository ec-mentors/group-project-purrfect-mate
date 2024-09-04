package purrfectMate.controllers.rest.internal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/status")
    public ResponseEntity<String> checkAuthStatus(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            // User is authenticated
            return ResponseEntity.ok("User is logged in");
        } else {
            // User is not authenticated
            return ResponseEntity.status(401).body("User not logged in");
        }
    }
}
