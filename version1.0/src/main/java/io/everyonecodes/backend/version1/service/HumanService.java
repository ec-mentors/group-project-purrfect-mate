package io.everyonecodes.backend.version1.service;

import io.everyonecodes.backend.version1.data.Human;
import io.everyonecodes.backend.version1.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HumanService {

    private final HumanRepository humanRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.authorities}")
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

    public Human createHuman(Human newHuman) {
        String password = newHuman.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        newHuman.setPassword(encryptedPassword);
        newHuman.setAuthorities(userAuthorities);
        return humanRepo.save(newHuman);
    }

}
