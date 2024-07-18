package io.everyonecodes.backend.version0.repository;

import io.everyonecodes.backend.version0.data.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findCatsByHumanId(Long humanId);
}
