package io.everyonecodes.backend.version1.endpoint;

import io.everyonecodes.backend.version1.data.Human;
import io.everyonecodes.backend.version1.service.HumanService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
