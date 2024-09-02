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
import purrfectmate.exceptions.WrongLoginDataException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HumanService {

    private final HumanRepository humanRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${credentials.user.authorities}")
    private Set<String> userAuthorities;

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

    public Human loginHuman(LoginDTO loginDTO) {

        System.out.println(loginDTO.getUsername());

        Optional<Human> human = humanRepo.findByUsername(loginDTO.getUsername());

        if (human.isPresent()) {

            return human.get();

        }

        throw new WrongLoginDataException();
    }
}
