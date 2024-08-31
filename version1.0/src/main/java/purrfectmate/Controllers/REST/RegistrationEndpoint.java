package purrfectmate.Controllers.REST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import purrfectmate.Exceptions.EmailAlreadyRegisteredException;
import purrfectmate.Exceptions.UsernameAlreadyTakenException;
import purrfectmate.data.Human;
import purrfectmate.data.HumanDTO;
import purrfectmate.service.HumanService;

@RestController
@RequestMapping("api/registration")
public class RegistrationEndpoint {

    private final HumanService service;

    public RegistrationEndpoint(HumanService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<?> addHuman(@RequestBody HumanDTO humanInputData) {

        try {
            Human newHuman = service.createHuman(humanInputData);
            return ResponseEntity.status(HttpStatus.CREATED).body(newHuman);

        } catch (UsernameAlreadyTakenException | EmailAlreadyRegisteredException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
