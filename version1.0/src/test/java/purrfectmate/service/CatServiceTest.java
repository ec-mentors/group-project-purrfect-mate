package purrfectmate.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
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
import java.util.Optional;

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
        Long id = human.getId();
        Mockito.when(catRepository.findCatsByHumanId(id)).thenReturn(cats);
        List<Cat> result = catService.findCatsByHumanId(id);
        assertEquals(cats, result);
        assertNotNull(result);
        Mockito.verify(catRepository).findCatsByHumanId(id);
    }

    @Test
    void findCatsByLocation() {
        String location = "location";
        Mockito.when(catRepository.findByLocation(location)).thenReturn(cats);
        List<Cat> result = catService.findCatsByLocation(location);
        assertEquals(cats, result);
        assertNotNull(result);
        Mockito.verify(catRepository).findByLocation(location);
    }

    @Test
    void createCatHumanFound() {
        Cat cat = new Cat();
        Long id = human.getId();

        Mockito.when(humanRepository.findById(id)).thenReturn(Optional.of(human));
        cat.setHuman(human);
        Mockito.when(catRepository.save(cat)).thenReturn(cat);

        Cat result = catService.createCat(cat, id);
        assertEquals(cat, result);

        Mockito.verify(humanRepository).findById(id);
        Mockito.verify(catRepository).save(cat);
    }

    @Test
    void createCatHumanNotFound() {
        Cat cat = new Cat();
        Long idThatIsNotAssignedToAnyHuman = 1L;

        Mockito.when(humanRepository.findById(idThatIsNotAssignedToAnyHuman)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            catService.createCat(cat, idThatIsNotAssignedToAnyHuman);
        });

        Mockito.verify(humanRepository).findById(idThatIsNotAssignedToAnyHuman);
        Mockito.verify(catRepository, Mockito.times(0)).save(cat);
    }

    @Test
    void deleteAllCats() {
        String deleted = "All cats deleted";
        String result = catService.deleteAllCats();

        Assertions.assertEquals(deleted, result);
        Mockito.verify(catRepository).deleteAll();
    }
}