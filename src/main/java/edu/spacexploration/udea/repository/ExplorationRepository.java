package edu.spacexploration.udea.repository;

import edu.spacexploration.udea.entity.Exploration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplorationRepository extends JpaRepository<Exploration, Integer> {}
