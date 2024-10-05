package edu.spacexploration.udea.service.impl;

import edu.spacexploration.udea.entity.CrewMember;
import edu.spacexploration.udea.repository.CrewMemberRepository;
import edu.spacexploration.udea.service.RandomHashingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomHashingServiceImpl implements RandomHashingService {

  private final CrewMemberRepository crewMemberRepository;

  @Override
  public void saveMembers(Integer explorationId, Integer spaceShipId, List<CrewMember> crewMembers) {
    crewMemberRepository.saveAll(crewMembers);
  }
}
