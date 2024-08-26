package purrfectmate.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import purrfectmate.data.Human;
import purrfectmate.data.HumanDTO;
import purrfectmate.repository.HumanRepository;

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

    public Human createHuman(HumanDTO inputHuman) {
        String username = inputHuman.getUsername();
        String email = inputHuman.getEmail();
        String password = inputHuman.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        String defaultLocation = "Austria";

        Human newHuman = new Human(username, email, encryptedPassword, defaultLocation);

        newHuman.setAuthorities(userAuthorities);

        return humanRepo.save(newHuman);
    }

}