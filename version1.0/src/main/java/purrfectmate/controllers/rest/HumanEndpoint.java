package purrfectmate.controllers.rest;



import org.springframework.web.bind.annotation.*;
import purrfectmate.data.entity.Human;
import purrfectmate.data.dto.HumanDTO;
import purrfectmate.service.HumanService;


@RestController
@RequestMapping("/humans")
public class HumanEndpoint {

    private final HumanService service;

    public HumanEndpoint(HumanService service) {
        this.service = service;
    }

    @PostMapping()
    public Human addHuman(@RequestBody HumanDTO humanInputData) {
        return service.createHuman(humanInputData);
    }
}
