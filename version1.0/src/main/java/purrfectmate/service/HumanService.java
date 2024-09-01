package purrfectmate.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import purrfectmate.data.dto.LoginDTO;
import purrfectmate.exceptions.EmailAlreadyRegisteredException;
import purrfectmate.exceptions.UsernameAlreadyTakenException;
import purrfectmate.data.entity.Human;
import purrfectmate.data.dto.RegisterDTO;
import purrfectmate.data.repository.HumanRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HumanService {

    private final HumanRepository humanRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${credentials.user.authorities}")
    Set<String> userAuthorities;

    public HumanService(HumanRepository humanRepo, PasswordEncoder passwordEncoder) {
        this.humanRepo = humanRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Human> getAllHumans() {
        return humanRepo.findAll();
    }

    public Optional<Human> getHumanById(Long id) {
        return humanRepo.findById(id);
    }

    public Human createHuman(RegisterDTO inputHuman) {

        if (humanRepo.existsByUsername(inputHuman.getUsername())) {
            throw new UsernameAlreadyTakenException();
        }
        else if (humanRepo.existsByEmail(inputHuman.getEmail())) {
            throw new EmailAlreadyRegisteredException();
        }


        String username = inputHuman.getUsername();
        String email = inputHuman.getEmail();
        String password = inputHuman.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        String defaultLocation = "Austria";

        Human newHuman = new Human(username, email, encryptedPassword, defaultLocation);
        newHuman.setAuthorities(userAuthorities);

        return humanRepo.save(newHuman);
    }

    public String loginHuman(LoginDTO loginDTO) {

        Optional<Human> human = humanRepo.findByUsername(loginDTO.getUsername());

        if (human.isPresent()) {
            if (passwordEncoder.matches(loginDTO.getPassword(), human.get().getPassword())) {
                return String.format(human.get().getUsername() + " logged in successfully");
            }
            return String.format(human.get().getUsername() + " provided wrong password");
        }

        return "User not found in database";
    }
}
