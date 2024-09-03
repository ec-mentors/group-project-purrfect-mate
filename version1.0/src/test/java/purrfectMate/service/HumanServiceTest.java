package purrfectMate.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import purrfectMate.Security.SecurityConfiguration;
import purrfectMate.data.dto.RegisterDTO;
import purrfectMate.data.entity.User;
import purrfectMate.data.repository.UserRepository;
import purrfectMate.exceptions.EmailAlreadyRegisteredException;
import purrfectMate.exceptions.UsernameAlreadyTakenException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class HumanServiceTest {

    @Autowired
    private UserService humanService;

    @MockBean
    private UserRepository humanRepository;

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

    private final List<User> humans = List.of(new User(), new User());
    private final User human = new User();

    private final String username = "username";
    private final String email = "email@email.com";
    private final String password = "password";
    private final RegisterDTO dto = new RegisterDTO(username, email, password);

    @Test
    void getAllHumansNoHumansInDatabase() {
        Mockito.when(humanRepository.findAll()).thenReturn(List.of());
        List<User> result = humanService.getAllUsers();
        assertEquals(List.of(), result);
        Mockito.verify(humanRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getAllHumansTwoHumansInDatabase() {
        Mockito.when(humanRepository.findAll()).thenReturn(humans);
        List<User> result = humanService.getAllUsers();
        assertEquals(humans, result);
        Mockito.verify(humanRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getHumanByIdHumanFound() {
        Long id = human.getId();
        Mockito.when(humanRepository.findById(id)).thenReturn(Optional.of(human));
        Optional<User> result = humanService.getHumanById(id);
        assertEquals(Optional.of(human), result);
        Mockito.verify(humanRepository).findById(id);
    }

    @Test
    void getHumanByIdHumanNotFound() {
        Long idThatLeadsToNoHuman = 1L;
        Mockito.when(humanRepository.findById(idThatLeadsToNoHuman)).thenReturn(Optional.empty());
        Optional<User> result = humanService.getHumanById(idThatLeadsToNoHuman);
        assertEquals(Optional.empty(), result);
        Mockito.verify(humanRepository).findById(idThatLeadsToNoHuman);
    }

    @Test
    void createHumanExistsByUsername() {
        Mockito.when(humanRepository.existsByUsername(username)).thenReturn(true);

        assertThrows(UsernameAlreadyTakenException.class, () -> {
            humanService.createUser(dto);
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
            humanService.createUser(dto);
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

        User newHuman = new User(dto.getUsername(), dto.getEmail(), encryptedPassword, defaultLocation);
        newHuman.setAuthorities(userAuthorities);

        Mockito.when(humanRepository.save(Mockito.any(User.class))).thenReturn(newHuman);

        User result = humanService.createUser(dto);
        assertEquals(newHuman, result);

        Mockito.verify(humanRepository, Mockito.times(1)).existsByUsername(dto.getUsername());
        Mockito.verify(humanRepository, Mockito.times(1)).existsByEmail(dto.getEmail());
        Mockito.verify(humanRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void loginHuman(){

    }
}