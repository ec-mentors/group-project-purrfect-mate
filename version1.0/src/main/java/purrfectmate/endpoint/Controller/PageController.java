package purrfectmate.endpoint.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/register")
    public String serveRegistration(){

        return "forward:/frontend/Register/registrationForm.html";
    }

    @GetMapping("/home")
    public String serveHome(){

        return "forward:/frontend/Home/home.html";
    }

    @GetMapping("/catProfile")
    public String serveCatProfile(){

        return "forward:/frontend/CatProfile/CatProfile.html";
    }

}
