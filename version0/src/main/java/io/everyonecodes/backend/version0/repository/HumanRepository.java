package io.everyonecodes.backend.version0.repository;

import io.everyonecodes.backend.version0.data.Human;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanRepository extends JpaRepository<Human, Long> {
}
