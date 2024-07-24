package io.everyonecodes.backend.version1.service;

import io.everyonecodes.backend.version1.data.Cat;
import io.everyonecodes.backend.version1.data.Human;
import io.everyonecodes.backend.version1.repository.CatRepository;
import io.everyonecodes.backend.version1.repository.HumanRepository;
import org.springframework.stereotype.Service;

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

    public List<Cat> findCatsByHumanId(long humanId) {
        return catRepo.findCatsByHumanId(humanId);
    }

    public Cat createCat(Cat cat) {

        // check if humanId in database
        // Human human = humanRepo.findById(humanId).orElseThrow(EntityNotFoundException::new);

        Human human = cat.getHuman();
        humanRepo.save(human);
        cat.setHuman(human);
        return catRepo.save(cat);
    }

    public String deleteAllCats() {
        catRepo.deleteAll();
        return "All cats deleted";
    }


}
