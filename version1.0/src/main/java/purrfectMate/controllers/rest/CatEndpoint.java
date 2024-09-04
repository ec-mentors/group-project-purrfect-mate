package purrfectMate.controllers.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import purrfectMate.service.CatService;

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