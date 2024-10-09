package edu.spacexploration.udea.service.impl;

import edu.spacexploration.udea.entity.CrewMember;
import edu.spacexploration.udea.entity.Exploration;
import edu.spacexploration.udea.entity.Spaceship;
import edu.spacexploration.udea.repository.CrewMemberRepository;
import edu.spacexploration.udea.repository.ExplorationRepository;
import edu.spacexploration.udea.repository.SpaceshipRepository;
import edu.spacexploration.udea.service.RandomHashingService;
import edu.spacexploration.udea.util.ChainedHashTable;
import edu.spacexploration.udea.util.CrewMemberKey;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomHashingServiceImpl implements RandomHashingService {

  private final CrewMemberRepository crewMemberRepository;
  private final SpaceshipRepository spaceshipRepository;
  private final ExplorationRepository explorationRepository;

  @Override
  public void saveMembers(
      Integer explorationId, Integer spaceShipId, List<CrewMember> crewMembers) {
    // 1. Get cabin_capacity and capacity for that spaceship_id from cache
    Spaceship spaceship = spaceshipRepository.findById(spaceShipId).get();
    Integer cabinCapacity = spaceship.getCabinCapacity();
    Integer capacity = spaceship.getCapacity();

    // 2. Iterate all the crew members and assign them to the spaceship
    for (CrewMember crewMember : crewMembers) {
      ChainedHashTable chainedHashTable = new ChainedHashTable(capacity);
      Integer familyId = crewMember.getFamilyId();
      List<Integer> hashValues;
      while (true) {
        hashValues = chainedHashTable.add(familyId);
        for (Integer cabinId : hashValues) {
          List<Integer> crewMembersInCabin =
              explorationRepository.findCrewMembersInCabin(explorationId, spaceShipId, cabinId);
          if (insertCrewMember(
              crewMember, crewMembersInCabin, cabinCapacity, spaceship, explorationId, cabinId)) {
            break;
          }
          if(crewMember.getAge()<=18){
            handleUnderageCrewMember(crewMember, crewMembersInCabin, cabinCapacity, spaceship, explorationId, cabinId);
          }
        }
        hashValues=hashValues.stream().map(n->n+1).toList();
      }
    }
    crewMemberRepository.saveAll(crewMembers);
  }

  private void handleUnderageCrewMember(CrewMember crewMember, List<Integer> crewMembersInCabin, Integer cabinCapacity, Spaceship spaceship, Integer explorationId, Integer cabinId) {
  }

  private boolean insertCrewMember(
      CrewMember crewMember,
      List<Integer> crewMembersInCabin,
      Integer cabinCapacity,
      Spaceship spaceship,
      Integer explorationId,
      Integer cabinId) {
    if (crewMembersInCabin.size() > cabinCapacity) {
      return false;
    }
    boolean newCrewMember = crewMemberRepository.findById(crewMember.getId()).isEmpty();
    if (newCrewMember) {
      crewMemberRepository.save(crewMember);
    }
    Exploration explorationByCrewMember =
        Exploration.builder()
            .crewMember(crewMember)
            .spaceship(spaceship)
            .id(CrewMemberKey.builder().id(explorationId).cabinId(cabinId).build())
            .build();
    explorationRepository.save(explorationByCrewMember);
    return true;
  }
}
