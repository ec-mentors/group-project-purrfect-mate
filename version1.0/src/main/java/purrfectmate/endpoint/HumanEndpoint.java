package purrfectmate.endpoint;



import org.springframework.web.bind.annotation.*;
import purrfectmate.data.Human;
import purrfectmate.service.HumanService;

import java.util.Optional;


@RestController
@RequestMapping("/humans")
public class HumanEndpoint {

    private final HumanService service;

    public HumanEndpoint(HumanService service) {
        this.service = service;
    }

    @PostMapping()
    public Human addHuman(@RequestBody Human human) {
        return service.createHuman(human);
    }

    @GetMapping("/human/{id}")
    public Human getHumanById(@PathVariable Long id) {
        return service.getHumanById(id).orElse(null);
    }
}