package io.everyonecodes.backend.version1.endpoint;


import io.everyonecodes.backend.version1.data.Human;
import io.everyonecodes.backend.version1.service.HumanService;
import org.springframework.web.bind.annotation.*;


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
