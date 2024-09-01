package purrfectmate.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import purrfectmate.data.entity.Cat;
import purrfectmate.data.entity.Human;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Human> {

    List<Cat> findCatsByHumanId(Long humanId);

    public final static String GET_CATS_BY_LOCATION = "SELECT * FROM cats WHERE location LIKE :location%";
    @Query(value = GET_CATS_BY_LOCATION, nativeQuery = true)
    List<Cat> findByLocation(@Param("location") String location);
}
