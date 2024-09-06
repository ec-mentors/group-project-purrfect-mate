package purrfectMate.service;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import purrfectMate.Security.UserPrincipal;
import purrfectMate.data.entity.Cat;
import purrfectMate.data.entity.User;
import purrfectMate.data.repository.CatRepository;
import purrfectMate.data.repository.UserRepository;

import java.sql.PreparedStatement;
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

        // check if the user is in the database
        User human = humanRepo.findById(humanId).orElseThrow(EntityNotFoundException::new);

        // make sure only the logged in user can post a cat to their account
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        if (!humanId.equals(userPrincipal.getId())) {
            throw new AccessDeniedException("User not allowed to add cat for this humanId");
        }

        cat.setHuman(human);
        

        return catRepo.save(cat);
    }

    public String deleteAllCats() {
        catRepo.deleteAll();
        return "All cats deleted";
    }
}