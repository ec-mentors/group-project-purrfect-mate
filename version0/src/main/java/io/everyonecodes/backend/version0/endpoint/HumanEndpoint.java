package io.everyonecodes.backend.version0.endpoint;


import io.everyonecodes.backend.version0.data.Human;
import io.everyonecodes.backend.version0.service.HumanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/humans")
public class HumanEndpoint {

    HumanService service;

    public HumanEndpoint(HumanService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Human> getAllHumans() {
        return service.getAllHumans();
    }

    @PostMapping()
    public Human addHuman(@RequestBody Human human) {
        return service.createHuman(human.getUsername(), human.getEmail());
    }
}
