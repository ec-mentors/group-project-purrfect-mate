package purrfectMate.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class PageController {

    private final String prefix = "forward:/frontend/";

    @GetMapping("/register")
    public String serveRegistration() {
        return prefix + "register/registrationForm.html";
    }

    @GetMapping("/login")
    public String serveLogin() {
        return prefix + "login/login.html";
    }

    @GetMapping("/home")
    @Secured("ROLE_USER")
    public String serveHome() {
        return prefix + "home/home.html";
    }

    @GetMapping("/catProfile")
    @Secured("ROLE_USER")
    public String serveCatProfile() {
        return prefix + "catProfile/CatProfile.html";
    }

}
