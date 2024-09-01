package purrfectmate.controllers.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import purrfectmate.data.dto.LoginDTO;
import purrfectmate.data.entity.Human;
import purrfectmate.exceptions.WrongLoginDataException;
import purrfectmate.service.HumanService;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginEndpointSecured {

    private final HumanService humanService;

    public LoginEndpointSecured(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping()
    public ResponseEntity<?> loginHuman(@RequestBody LoginDTO loginDTO) {

        System.out.println(loginDTO.getUsername());

        try {
            Human human = humanService.loginHuman(loginDTO);
            return ResponseEntity.status(HttpStatus.OK).body(human);
        }catch (WrongLoginDataException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public List<Human> getAllHumans() {
        return humanService.getAllHumans();
    }
}
