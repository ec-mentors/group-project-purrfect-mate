package purrfectmate.endpoint.Endpoint;



import org.springframework.web.bind.annotation.*;
import purrfectmate.data.Human;
import purrfectmate.data.HumanDTO;
import purrfectmate.service.HumanService;


@RestController
@RequestMapping("api/registration")
public class RegistrationEndpoint {

    private final HumanService service;

    public RegistrationEndpoint(HumanService service) {
        this.service = service;
    }

    @PostMapping()
    public Human addHuman(@RequestBody HumanDTO humanInputData) {
        System.out.println("!!!Register Endpoint reached!!! Human: " + humanInputData);
        return service.createHuman(humanInputData);
    }
}
