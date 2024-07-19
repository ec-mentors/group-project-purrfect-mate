package io.everyonecodes.backend.version0.service;

import io.everyonecodes.backend.version0.data.Cat;
import io.everyonecodes.backend.version0.data.Human;
import io.everyonecodes.backend.version0.repository.CatRepository;
import io.everyonecodes.backend.version0.repository.HumanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {

    private final HumanRepository humanRepo;
    private final CatRepository catRepo;

    public CatService(HumanRepository humanRepo, CatRepository catRepo) {
        this.humanRepo = humanRepo;
        this.catRepo = catRepo;
    }

    public List<Cat> getAllCats() {
        return catRepo.findAll();
    }

    public List<Cat> findCatsByHumanId(long humanId) {
        return catRepo.findCatsByHumanId(humanId);
    }

    public Cat createCat(String name, Long humanId) {

        // check if humanId in database
        // Human human = humanRepo.findById(humanId).orElseThrow(EntityNotFoundException::new);

        Cat newCat = new Cat(name, humanId);
        return catRepo.save(newCat);
    }

    public String deleteAllCats() {
        catRepo.deleteAll();
        return "All cats deleted";
    }


}
