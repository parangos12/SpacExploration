package edu.spacexploration.udea.repository;

import edu.spacexploration.udea.entity.Spaceship;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Integer> {

  @Cacheable("${spring.cache.cache-names}")
  Optional<Spaceship> findById(Integer id);
}
