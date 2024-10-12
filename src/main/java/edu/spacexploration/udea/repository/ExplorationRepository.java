package edu.spacexploration.udea.repository;

import edu.spacexploration.udea.entity.Exploration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ExplorationRepository extends JpaRepository<Exploration, Integer> {

  @Query(
      "select ex.id.crewMember.id from Exploration ex where ex.spaceship.id =:spaceShipId and ex.id.id =:explorationId and ex.id.cabinId =:cabinId")
  List<String> findCrewMembersInCabin(Integer explorationId, Integer spaceShipId, Integer cabinId);

  @Modifying
  @Query(
      "delete from Exploration ex where ex.id.id =:explorationId and ex.spaceship.id =:spaceShipId and ex.id.crewMember.id =:crewMemberId")
  void deleteByExplorationIdAndSpaceShipIdAndCrewMemberId(Integer explorationId, Integer spaceShipId, String crewMemberId);

  @Query(
      value = "SELECT cabin_id " +
          "FROM (" +
          "  SELECT cabin_id, COUNT(*) AS crew_count " +
          "  FROM exploration " +
          "  WHERE id = :explorationId " +
          "  GROUP BY cabin_id" +
          ") AS cabin_counts " +
          "WHERE crew_count < :cabinCapacity " +
          "ORDER BY crew_count " +
          "LIMIT 1",
      nativeQuery = true
  )
  Integer findAvailableCabin(@Param("explorationId") Integer explorationId, @Param("cabinCapacity") Integer cabinCapacity);
}
