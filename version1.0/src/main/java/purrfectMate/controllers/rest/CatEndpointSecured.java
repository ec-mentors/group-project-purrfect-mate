package purrfectMate.controllers.rest;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import purrfectMate.Security.UserPrincipal;
import purrfectMate.data.dto.CatResponseDTO;
import purrfectMate.data.entity.Cat;
import purrfectMate.service.CatService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatEndpointSecured {

    private final Logger logger;
    private final CatService catService;

    public CatEndpointSecured(CatService catService, Logger logger) {
        this.catService = catService;
        this.logger = logger;
    }

    @GetMapping("/{humanId}/cats")
    @Secured("ROLE_ADMIN")
    public List<Cat> getCatsByHumanId(@PathVariable Long humanId) {
        return catService.findCatsByHumanId(humanId);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CatResponseDTO> getCatById(@PathVariable Long catId) throws IOException {
        return catService.getCatWithImageById(catId);
    }

    @GetMapping("/numberOfCatsInDatabase")
    public int getNumberOfCatsInDatabase() {
        int numberOfCatsInDatabase = catService.returnNumberOfCatsInDatabase();
        logger.debug("Number of cats in database: {}", numberOfCatsInDatabase);
        return numberOfCatsInDatabase;
    }

    @GetMapping()
    public List<Cat> getCatsByLocation() {

        // Retrieve the logged-in user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        // Get the user's location
        String userLocation = userDetails.getLocation();

        // Use the user's location to find cats only in that location
        return catService.findCatsByLocation(userLocation);
    }


    @PostMapping("/{humanId}/registerCat")
    public ResponseEntity<Cat> addCat(@PathVariable Long humanId,
                                 @RequestPart("cat") Cat cat,      // RequestPart is necessary because RequestBody would work with JSON but picture can't be included in JSON
                                 @RequestParam("file") MultipartFile file) {

        try {
            // Check if the cat object is correctly populated
            // System.out.println("Received Cat data: " + cat);
            return ResponseEntity.status(HttpStatus.CREATED).body(catService.createCat(cat, file, humanId));
        } catch (IOException e) {
            logger.debug("IOException while writing image: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping()
    @Secured("ROLE_ADMIN")
    public String deleteAllCats() {
        return catService.deleteAllCats();
    }
}
