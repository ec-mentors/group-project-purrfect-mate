package io.everyonecodes.backend.version1.repository;

import io.everyonecodes.backend.version1.data.Cat;
import io.everyonecodes.backend.version1.data.Human;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Human> {

    List<Cat> findCatsByHumanId(Long humanId);
}
