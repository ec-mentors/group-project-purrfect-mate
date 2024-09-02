package purrfectMate.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import purrfectMate.data.entity.Cat;
import purrfectMate.data.entity.User;
import purrfectMate.data.repository.CatRepository;
import purrfectMate.data.repository.UserRepository;

import java.util.List;

@Service
public class CatService {

    private final UserRepository humanRepo;
    private final CatRepository catRepo;

    public CatService(UserRepository humanRepo, CatRepository catRepo) {
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
        User human = humanRepo.findById(humanId).orElseThrow(EntityNotFoundException::new);

        cat.setHuman(human);
        return catRepo.save(cat);
    }

    public String deleteAllCats() {
        catRepo.deleteAll();
        return "All cats deleted";
    }
}