package purrfectMate.controllers;

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

    @GetMapping("/home")
    public String serveHome() {
        return prefix + "home/home.html";
    }

    @GetMapping("/catProfile")
    public String serveCatProfile() {
        return prefix + "catProfile/CatProfile.html";
    }

    @GetMapping("/login")
    public String serveLogin() {
        return prefix + "login/login.html";
    }

    @GetMapping("/nav")
    public String serveNav() {
        return "/frontend/global/nav.html";
    }
}
