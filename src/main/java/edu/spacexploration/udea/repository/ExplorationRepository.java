package edu.spacexploration.udea.repository;

import edu.spacexploration.udea.entity.Exploration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExplorationRepository extends JpaRepository<Exploration, Integer> {

  @Query(
      "select ex.crewMember.id from Exploration ex where ex.spaceship.id =:spaceShipId and ex.id.id =:explorationId and ex.id.cabinId =:cabinId")
  List<Integer> findCrewMembersInCabin(Integer explorationId, Integer spaceShipId, Integer cabinId);

}
