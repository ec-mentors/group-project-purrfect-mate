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

    @GetMapping("/registerCat")
    public String serveCatRegistration() {
        return prefix + "registercat/catForm.html";
    }

    @GetMapping("/home")
    public String serveHome() {
        return prefix + "home/home.html";
    }

    @GetMapping("/login")
    public String serveLogin() {
        return prefix + "login/login.html";
    }

    @GetMapping("/nav")
    public String serveNav() {
        return prefix + "global/nav.html";
    }

    @GetMapping("/browse")
    public String serveCatProfile() {
        return prefix + "catProfile/catProfile.html";
    }

    @GetMapping
    public String serveHomePage() {
        return "/home";
    }
}
