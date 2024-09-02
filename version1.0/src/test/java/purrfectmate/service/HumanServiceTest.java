package purrfectmate.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import purrfectmate.config.SecurityConfiguration;
import purrfectmate.data.dto.RegisterDTO;
import purrfectmate.data.entity.Human;
import purrfectmate.data.repository.HumanRepository;
import purrfectmate.exceptions.EmailAlreadyRegisteredException;
import purrfectmate.exceptions.UsernameAlreadyTakenException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Value("${credentials.user.authorities}")
    private Set<String> userAuthorities;

    private final List<Human> humans = List.of(new Human(), new Human());
    private final Human human = new Human();

    private final String username = "username";
    private final String email = "email@email.com";
    private final String password = "password";
    private final RegisterDTO dto = new RegisterDTO(username, email, password);

    @Test
    void getAllHumansNoHumansInDatabase() {
        Mockito.when(humanRepository.findAll()).thenReturn(List.of());
        List<Human> result = humanService.getAllHumans();
        assertEquals(List.of(), result);
        Mockito.verify(humanRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getAllHumansTwoHumansInDatabase() {
        Mockito.when(humanRepository.findAll()).thenReturn(humans);
        List<Human> result = humanService.getAllHumans();
        assertEquals(humans, result);
        Mockito.verify(humanRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getHumanByIdHumanFound() {
        Long id = human.getId();
        Mockito.when(humanRepository.findById(id)).thenReturn(Optional.of(human));
        Optional<Human> result = humanService.getHumanById(id);
        assertEquals(Optional.of(human), result);
        Mockito.verify(humanRepository).findById(id);
    }

    @Test
    void getHumanByIdHumanNotFound() {
        Long idThatLeadsToNoHuman = 1L;
        Mockito.when(humanRepository.findById(idThatLeadsToNoHuman)).thenReturn(Optional.empty());
        Optional<Human> result = humanService.getHumanById(idThatLeadsToNoHuman);
        assertEquals(Optional.empty(), result);
        Mockito.verify(humanRepository).findById(idThatLeadsToNoHuman);
    }

    @Test
    void createHumanExistsByUsername() {
        Mockito.when(humanRepository.existsByUsername(username)).thenReturn(true);

        assertThrows(UsernameAlreadyTakenException.class, () -> {
            humanService.createHuman(dto);
        });

        Mockito.verify(humanRepository, Mockito.times(1)).existsByUsername(username);
        Mockito.verify(humanRepository, Mockito.times(0)).existsByEmail(email);
        Mockito.verify(humanRepository, Mockito.times(0)).save(human);
    }

    @Test
    void createHumanExistsByEmail() {
        Mockito.when(humanRepository.existsByUsername(dto.getUsername())).thenReturn(false);
        Mockito.when(humanRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyRegisteredException.class, () -> {
            humanService.createHuman(dto);
        });

        Mockito.verify(humanRepository, Mockito.times(1)).existsByUsername(dto.getUsername());
        Mockito.verify(humanRepository, Mockito.times(1)).existsByEmail(dto.getEmail());
        Mockito.verify(humanRepository, Mockito.times(0)).save(human);
    }

    @Test
    void createHumanSuccess() {
        Mockito.when(humanRepository.existsByUsername(dto.getUsername())).thenReturn(false);
        Mockito.when(humanRepository.existsByEmail(dto.getEmail())).thenReturn(false);

        String defaultLocation = "Austria";
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());

        Human newHuman = new Human(dto.getUsername(), dto.getEmail(), encryptedPassword, defaultLocation);
        newHuman.setAuthorities(userAuthorities);

        Mockito.when(humanRepository.save(Mockito.any(Human.class))).thenReturn(newHuman);

        Human result = humanService.createHuman(dto);
        assertEquals(newHuman, result);

        Mockito.verify(humanRepository, Mockito.times(1)).existsByUsername(dto.getUsername());
        Mockito.verify(humanRepository, Mockito.times(1)).existsByEmail(dto.getEmail());
        Mockito.verify(humanRepository, Mockito.times(1)).save(Mockito.any(Human.class));
    }

    @Test
    void loginHuman(){

    }
}