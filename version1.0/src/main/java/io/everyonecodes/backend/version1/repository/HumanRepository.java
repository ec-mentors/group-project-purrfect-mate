package io.everyonecodes.backend.version1.repository;

import io.everyonecodes.backend.version1.data.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HumanRepository extends JpaRepository<Human, Long> {

    public Optional<Human> findByUsername(String username);

    public Optional<Human> findById(Long id);

}
