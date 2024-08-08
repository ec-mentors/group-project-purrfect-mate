package io.everyonecodes.backend.version1.endpoint;

import io.everyonecodes.backend.version1.data.Cat;
import io.everyonecodes.backend.version1.service.CatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cats")
public class CatEndpoint {


    private final CatService catService;

    public CatEndpoint(CatService catService) {
        this.catService = catService;
    }

    @GetMapping()
    public List<Cat> getAllCats() {
        return catService.getAllCats();
    }



}