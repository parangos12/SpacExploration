package edu.spacexploration.udea.repository;

import edu.spacexploration.udea.entity.CrewMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, String> {

  List<CrewMember> findFirstByAgeGreaterThanEqualAndIdIn(Integer age, List<String> crewMembersInCabin);

}
