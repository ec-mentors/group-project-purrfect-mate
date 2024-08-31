package purrfectmate.Controllers.REST;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import purrfectmate.config.SecurityService;
import purrfectmate.config.UserPrincipal;
import purrfectmate.data.Cat;
import purrfectmate.service.CatService;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatEndpointSecured {

    private final CatService catService;
    private final SecurityService securityService;

    public CatEndpointSecured(CatService catService, SecurityService securityService) {
        this.catService = catService;
        this.securityService = securityService;
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

    @PostMapping("/{humanId}/addCat")
    @PreAuthorize("@securityService.hasUserId(#humanId)")
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
