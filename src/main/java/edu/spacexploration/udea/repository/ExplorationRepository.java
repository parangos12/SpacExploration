package edu.spacexploration.udea.repository;

import edu.spacexploration.udea.entity.Exploration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplorationRepository extends JpaRepository<Exploration, Integer> {

  @Query(
      "select ex.crewMember.id from exploration ex where ex.spaceship.id =:spaceShipId and ex.spaceship.exploration.id =:explorationId and ex.spaceship.cabin.id =:cabinId")
  List<Integer> findCrewMembersInCabin(Integer explorationId, Integer spaceShipId, Integer cabinId);
}
