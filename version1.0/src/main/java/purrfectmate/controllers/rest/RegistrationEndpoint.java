package purrfectmate.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import purrfectmate.exceptions.EmailAlreadyRegisteredException;
import purrfectmate.exceptions.UsernameAlreadyTakenException;
import purrfectmate.data.entity.Human;
import purrfectmate.data.dto.RegisterDTO;
import purrfectmate.service.HumanService;

@RestController
@RequestMapping("api/registration")
public class RegistrationEndpoint {

    private final HumanService service;

    public RegistrationEndpoint(HumanService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> addHuman(@RequestBody RegisterDTO humanInputData) {

        try {
            Human newHuman = service.createHuman(humanInputData);
            return ResponseEntity.status(HttpStatus.CREATED).body(newHuman);

        } catch (UsernameAlreadyTakenException | EmailAlreadyRegisteredException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
