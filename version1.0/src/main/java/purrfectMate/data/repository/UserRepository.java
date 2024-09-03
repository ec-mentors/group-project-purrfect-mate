package purrfectMate.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import purrfectMate.data.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    public boolean existsByEmail(String email);

    public boolean existsByUsername(String username);

    public Optional<User> findByEmail(String email);

    public Optional<User> findById(Long id);

}
