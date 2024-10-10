package edu.spacexploration.udea.repository;

import edu.spacexploration.udea.entity.CrewMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, String> {

  @Query(
      "select * from crewMember cm where cm.age>=18 and cm.id in :crewMembersInCabin limit 1")
  CrewMember findAdultCrewMembersInCabin(List<Integer> crewMembersInCabin);

}
