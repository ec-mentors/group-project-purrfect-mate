package purrfectMate.controllers.rest;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import purrfectMate.Security.UserPrincipal;
import purrfectMate.data.entity.Cat;
import purrfectMate.service.CatService;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatEndpointSecured {

    private final CatService catService;

    public CatEndpointSecured(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/{humanId}")
    @Secured("ROLE_ADMIN")
    public List<Cat> getCatsByHumanId(@PathVariable Long humanId) {
        return catService.findCatsByHumanId(humanId);
    }

    @GetMapping()
    @Secured("ROLE_USER")
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
    @PreAuthorize("#humanId == authentication.principal.userId")
    @Secured("ROLE_USER")
    public Cat addCat(@PathVariable Long humanId, @RequestBody Cat cat) {
        return catService.createCat(cat, humanId);
    }

    @DeleteMapping()
    @Secured("ROLE_ADMIN")
    public String deleteAllCats() {
        return catService.deleteAllCats();
    }
}
