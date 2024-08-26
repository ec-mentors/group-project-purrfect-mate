package purrfectmate.endpoint;



import org.springframework.web.bind.annotation.*;
import purrfectmate.data.Human;
import purrfectmate.data.HumanDTO;
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
