package purrfectmate.endpoint;


import org.springframework.web.bind.annotation.*;
import purrfectmate.service.CatService;

import java.util.List;


@RestController
@RequestMapping("/cats")
public class CatEndpoint {


    private final CatService catService;

    public CatEndpoint(CatService catService) {
        this.catService = catService;
    }

//    @GetMapping()
//    public List<Cat> getAllCats() {
//        return catService.getAllCats();
//    }



}