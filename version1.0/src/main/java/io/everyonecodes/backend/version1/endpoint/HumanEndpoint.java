package io.everyonecodes.backend.version1.endpoint;


import io.everyonecodes.backend.version1.data.Human;
import io.everyonecodes.backend.version1.service.HumanService;
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
