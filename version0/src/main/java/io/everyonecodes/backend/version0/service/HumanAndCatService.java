package io.everyonecodes.backend.version0.service;

import io.everyonecodes.backend.version0.data.Human;
import io.everyonecodes.backend.version0.repository.CatRepository;
import io.everyonecodes.backend.version0.repository.HumanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanAndCatService {

    private final HumanRepository humanRepo;
    private final CatRepository catRepo;

    public HumanAndCatService(HumanRepository humanRepo, CatRepository catRepo) {
        this.humanRepo = humanRepo;
        this.catRepo = catRepo;
    }

    public List<Human> getAllHumans() {
        return humanRepo.findAll();
    }

    public Human createHuman(String username, String email) {
        Human newHuman = new Human();
        newHuman.setUsername(username);
        newHuman.setEmail(email);
        return humanRepo.save(newHuman);
    }

}
