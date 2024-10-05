package edu.spacexploration.udea.service;

import edu.spacexploration.udea.entity.CrewMember;
import java.util.List;

public interface RandomHashingService {

  void saveMembers(Integer explorationId, Integer spaceShipId, List<CrewMember> crewMember);

}
