package io.everyonecodes.backend.version1.endpoint;

import io.everyonecodes.backend.version1.config.SecurityService;
import io.everyonecodes.backend.version1.data.Cat;
import io.everyonecodes.backend.version1.service.CatService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping()
    @Secured("ROLE_ADMIN")
    public String deleteAllCats() {
        return catService.deleteAllCats();
    }

    @PostMapping("/{humanId}/addCat")
    @PreAuthorize("@securityService.hasUserId(#humanId)")
    @Secured("ROLE_USER")
    public Cat addCat(@PathVariable Long humanId, @RequestBody Cat cat) {
        return catService.createCat(cat, humanId);
    }
}
