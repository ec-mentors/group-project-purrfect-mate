package purrfectmate.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import purrfectmate.config.SecurityConfiguration;
import purrfectmate.data.entity.Human;
import purrfectmate.data.repository.HumanRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class HumanServiceTest {

    @Autowired
    private HumanService humanService;

    @MockBean
    private HumanRepository humanRepository;

    @MockBean
    SecurityConfiguration securityConfiguration;

    @MockBean
    HttpSecurity httpSecurity;

    @MockBean
    SecurityFilterChain securityFilterChain;

    @MockBean
    PasswordEncoder passwordEncoder;

    private final Human human = new Human();

    @Test
    void getAllHumans() {
    }

    @Test
    void getHumanById() {
        Long id = human.getId();
        Mockito.when(humanRepository.findById(id)).thenReturn(Optional.of(human));
        Optional<Human> result = humanService.getHumanById(id);
        assertEquals(Optional.of(human), result);
        assertNotNull(result);
        Mockito.verify(humanRepository).findById(id);
    }

    @Test
    void createHuman() {
    }
}