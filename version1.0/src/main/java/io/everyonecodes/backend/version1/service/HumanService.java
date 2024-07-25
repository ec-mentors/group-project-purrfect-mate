package io.everyonecodes.backend.version1.service;

import io.everyonecodes.backend.version1.data.Human;
import io.everyonecodes.backend.version1.repository.HumanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HumanService {

    private final HumanRepository humanRepo;

    public HumanService(HumanRepository humanRepo) {
        this.humanRepo = humanRepo;
    }

    public List<Human> getAllHumans() {
        return humanRepo.findAll();
    }

    public Optional<Human> getHumanById(Long id) {
        return humanRepo.findById(id);
    }

    public Human createHuman(String username, String email) {
        Human newHuman = new Human();
        newHuman.setUsername(username);
        newHuman.setEmail(email);
        return humanRepo.save(newHuman);
    }

}
