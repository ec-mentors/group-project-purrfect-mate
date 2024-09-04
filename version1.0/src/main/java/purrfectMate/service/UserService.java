package purrfectMate.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import purrfectMate.exceptions.EmailAlreadyRegisteredException;
import purrfectMate.exceptions.UsernameAlreadyTakenException;
import purrfectMate.data.entity.User;
import purrfectMate.data.dto.RegisterDTO;
import purrfectMate.data.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    String defaultLocation = "Austria";

    private final Logger logger;

    @Value("${authorities.user}")
    private String userAuthority;

    @Value("${authorities.admin}")
    private String adminAuthority;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, Logger logger) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.logger = logger;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public User createUser(RegisterDTO inputHuman) {
        logger.debug("Attempting to create User: {}", inputHuman);
        return createUserWithAuthorities(inputHuman, Set.of(userAuthority));
    }

    public User createAdmin(RegisterDTO inputHuman) {
        logger.debug("Attempting to create Admin: {}", inputHuman);
        return createUserWithAuthorities(inputHuman, Set.of(userAuthority, adminAuthority));
    }

    private User createUserWithAuthorities(RegisterDTO inputHuman, Set<String> authorities) {
        String username = inputHuman.getUsername();
        String email = inputHuman.getEmail();
        String password = inputHuman.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);

        validateUserDetails(username, email);

        User newHuman = new User(username, email, encryptedPassword, defaultLocation);
        newHuman.setAuthorities(authorities);

        return userRepo.save(newHuman);
    }

    private void validateUserDetails(String username, String email) {
        if (userRepo.existsByUsername(username)) {
            logger.debug("Username {} already exists, aborting user creation...", username);
            throw new UsernameAlreadyTakenException();
        } else if (userRepo.existsByEmail(email)) {
            logger.debug("Email {} already exists, aborting user creation...", email);
            throw new EmailAlreadyRegisteredException();
        }
    }

    public boolean usernameExists(String username) {
        return userRepo.existsByUsername(username);
    }

}
