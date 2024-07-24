package io.everyonecodes.backend.version1.endpoint;

import io.everyonecodes.backend.version1.data.Cat;
import io.everyonecodes.backend.version1.service.CatService;
import io.everyonecodes.backend.version1.service.HumanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cats")
public class CatEndpoint {

    private final HumanService humanService;
    private final CatService catService;

    public CatEndpoint(HumanService humanService, CatService catService) {
        this.humanService = humanService;
        this.catService = catService;
    }

    @GetMapping()
    public List<Cat> getAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/{humanId}")
    public List<Cat> getCatsByHumanId(@PathVariable long humanId) {
        return catService.findCatsByHumanId(humanId);
    }

    // adding cats has this weird bug of creating the same cat over and over
    @PostMapping()
    public Cat addCat(@RequestBody Cat cat) {
        return catService.createCat(cat);
    }

    @DeleteMapping()
    public String deleteAllCats() {
        return catService.deleteAllCats();
    }
}