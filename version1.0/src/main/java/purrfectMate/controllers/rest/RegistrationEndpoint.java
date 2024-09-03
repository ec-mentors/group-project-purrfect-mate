package purrfectMate.controllers.rest;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import purrfectMate.exceptions.EmailAlreadyRegisteredException;
import purrfectMate.exceptions.UsernameAlreadyTakenException;
import purrfectMate.data.entity.User;
import purrfectMate.data.dto.RegisterDTO;
import purrfectMate.service.UserService;

@RestController
@RequestMapping("api/registration")
public class RegistrationEndpoint {

    private final UserService service;
    private final Logger logger;

    public RegistrationEndpoint(UserService service, Logger logger) {

        this.service = service;
        this.logger = logger;

    }

    @PostMapping()
    public ResponseEntity<?> addHuman(@RequestBody RegisterDTO humanInputData) {

        logger.debug("Reached registration endpoint with {}", humanInputData);

        try {

            User newHuman = service.createUser(humanInputData);
            logger.debug("created new human {}", newHuman);

            return ResponseEntity.status(HttpStatus.CREATED).body(newHuman);

        } catch (UsernameAlreadyTakenException | EmailAlreadyRegisteredException e) {

            logger.debug("{} for {}", e.getMessage(), humanInputData);

            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }
    }
}
