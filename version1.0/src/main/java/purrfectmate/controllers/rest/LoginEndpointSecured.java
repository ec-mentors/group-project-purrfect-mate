package purrfectmate.controllers.rest;


import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import purrfectmate.data.dto.LoginDTO;
import purrfectmate.data.entity.Human;
import purrfectmate.service.HumanService;

import java.util.List;

@RestController
@RequestMapping("api/login")
public class LoginEndpointSecured {

    private final HumanService service;
    private final HumanService humanService;

    public LoginEndpointSecured(HumanService service, HumanService humanService) {
        this.service = service;
        this.humanService = humanService;
    }

    @PostMapping()
    public String loginHuman(@RequestBody LoginDTO loginDTO) {

        return humanService.loginHuman(loginDTO);
        
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public List<Human> getAllHumans() {
        return service.getAllHumans();
    }
}
