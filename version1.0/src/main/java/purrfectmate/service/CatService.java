package purrfectmate.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import purrfectmate.data.entity.Cat;
import purrfectmate.data.entity.Human;
import purrfectmate.data.repository.CatRepository;
import purrfectmate.data.repository.HumanRepository;

import java.util.List;

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

    public List<Cat> findCatsByHumanId(Long humanId) {
        return catRepo.findCatsByHumanId(humanId);
    }

    public List<Cat> findCatsByLocation(String location) {
        return catRepo.findByLocation(location);
    }

    public Cat createCat(Cat cat, Long humanId) {

        // check if humanId in database
        Human human = humanRepo.findById(humanId).orElseThrow(EntityNotFoundException::new);

        cat.setHuman(human);
        return catRepo.save(cat);
    }

    public String deleteAllCats() {
        catRepo.deleteAll();
        return "All cats deleted";
    }
}