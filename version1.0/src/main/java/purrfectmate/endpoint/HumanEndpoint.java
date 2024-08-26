package purrfectmate.endpoint;



import org.springframework.web.bind.annotation.*;
import purrfectmate.data.Human;
import purrfectmate.service.HumanService;


@RestController
@RequestMapping("/humans")
public class HumanEndpoint {

    HumanService service;

    public HumanEndpoint(HumanService service) {
        this.service = service;
    }

    @PostMapping()
    public Human addHuman(@RequestBody Human human) {
        return service.createHuman(human);
    }
}
