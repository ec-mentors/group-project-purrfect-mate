package purrfectMate.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import purrfectMate.data.dto.LoginDTO;
import purrfectMate.exceptions.EmailAlreadyRegisteredException;
import purrfectMate.exceptions.UsernameAlreadyTakenException;
import purrfectMate.data.entity.User;
import purrfectMate.data.dto.RegisterDTO;
import purrfectMate.data.repository.UserRepository;
import purrfectMate.exceptions.WrongLoginDataException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    private final Logger logger;

    @Value("${credentials.user.authorities}")
    private Set<String> userAuthorities;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, Logger logger) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.logger = logger;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getHumanById(Long id) {
        return userRepo.findById(id);
    }

    public User createUser(RegisterDTO inputHuman) {

        logger.info("Attempting to create User: " + inputHuman);

        String username = inputHuman.getUsername();
        String email = inputHuman.getEmail();
        String password = inputHuman.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        String defaultLocation = "Austria";

        if (userRepo.existsByUsername(username)) {
            logger.debug("Username {} already exists", username);
            throw new UsernameAlreadyTakenException();
        }
        else if (userRepo.existsByEmail(email)) {
            logger.debug("Email {} already exists", email);
            throw new EmailAlreadyRegisteredException();
        }

        User newHuman = new User(username, email, encryptedPassword, defaultLocation);
        newHuman.setAuthorities(userAuthorities);

        return userRepo.save(newHuman);
    }

    public User loginUser(LoginDTO loginDTO) {
        // Log the login attempt at the start to capture input values
        logger.debug("Attempting login for user: {}", loginDTO);

        // Find the user by username or throw an exception if not found
        User user = userRepo.findByUsername(loginDTO.getUserName())
                .orElseThrow(() -> {
                    logger.debug("Username {} not found", loginDTO.getUserName());
                    return new WrongLoginDataException();
                });

        // Check if password matches user
        if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            logger.debug("Password { match for username: {}", loginDTO.getUserName());
            return user;
        }
        else {
            // Log and throw an exception if the password does not match
            logger.debug("Credentials do not match for username: {}", loginDTO.getUserName());
            throw new WrongLoginDataException();
        }
    }
}
