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
import purrfectmate.data.entity.Cat;
import purrfectmate.data.entity.Human;
import purrfectmate.data.repository.CatRepository;
import purrfectmate.data.repository.HumanRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CatServiceTest {

    @Autowired
    private CatService catService;

    @MockBean
    private CatRepository catRepository;

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

    private final List<Cat> cats = List.of();
    private final Human human = new Human();

    @Test
    void getAllCats() {
        Mockito.when(catRepository.findAll()).thenReturn(cats);
        assertEquals(cats, catService.getAllCats());
        Mockito.verify(catRepository).findAll();
    }

    @Test
    void findCatsByHumanId() {

    }

    @Test
    void findCatsByLocation() {

    }

    @Test
    void createCat() {
    }

    @Test
    void deleteAllCats() {
    }
}