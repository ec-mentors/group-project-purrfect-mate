package io.everyonecodes.backend.version1.repository;

import io.everyonecodes.backend.version1.data.Human;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HumanRepository extends JpaRepository<Human, Long> {

}
