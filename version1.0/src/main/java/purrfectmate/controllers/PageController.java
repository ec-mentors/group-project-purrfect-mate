package purrfectmate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("SpringMVCViewInspection")
@Controller
public class PageController {

    private final String prefix = "forward:/frontend/";

    @GetMapping("/register")
    public String serveRegistration() {
        return prefix + "Register/registrationForm.html";
    }

    @GetMapping("/home")
    public String serveHome() {
        return prefix + "Home/home.html";
    }

    @GetMapping("/catProfile")
    public String serveCatProfile() {
        return prefix + "CatProfile/CatProfile.html";
    }
}
