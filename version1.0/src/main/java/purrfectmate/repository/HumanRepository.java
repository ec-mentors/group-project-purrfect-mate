package purrfectmate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import purrfectmate.data.Human;

import java.util.Optional;


public interface HumanRepository extends JpaRepository<Human, Long> {

    public Optional<Human> findByUsername(String username);

    public Optional<Human> findById(Long id);

}
