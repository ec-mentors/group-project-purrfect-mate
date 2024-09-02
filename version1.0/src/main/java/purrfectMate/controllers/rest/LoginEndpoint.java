package purrfectMate.controllers.rest;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import purrfectMate.data.dto.LoginDTO;
import purrfectMate.data.entity.User;
import purrfectMate.exceptions.WrongLoginDataException;
import purrfectMate.service.UserService;

@RestController
@RequestMapping("/api/login")
public class LoginEndpoint {

    private final UserService humanService;
    private final Logger logger;

    public LoginEndpoint(UserService humanService, Logger logger) {
        this.humanService = humanService;
        this.logger = logger;
    }

    @PostMapping()
    public ResponseEntity<?> loginHuman(@RequestBody LoginDTO loginDTO) {

        logger.debug("Reached login endpoint with: {}", loginDTO.toString());
        try {
            User human = humanService.loginUser(loginDTO);
            return ResponseEntity.status(HttpStatus.OK).body(human);
        } catch (WrongLoginDataException e) {
            logger.debug("Wrong login data");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }
}
