package purrfectmate.endpoint;


import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import purrfectmate.data.Human;
import purrfectmate.service.HumanService;

import java.util.List;

@RestController
@RequestMapping("/humans")
public class HumanEndpointSecured {

    private final HumanService service;

    public HumanEndpointSecured(HumanService service) {
        this.service = service;
    }

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public List<Human> getAllHumans() {
        return service.getAllHumans();

    }

    @GetMapping("/human/{id}")
    public Human getHumanById(@PathVariable Long id) {
        return service.getHumanById(id).orElse(null);
    }
}
