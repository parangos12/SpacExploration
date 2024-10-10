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

  public static final int MIN_AGE = 18;
  private final CrewMemberRepository crewMemberRepository;
  private final SpaceshipRepository spaceshipRepository;
  private final ExplorationRepository explorationRepository;

  @Override
  public void saveMembers(
      Integer explorationId, Integer spaceShipId, List<CrewMember> crewMembers) {
    // 1. Get cabin_capacity and capacity for that spaceship_id from cache
    //TODO: Implement cache.
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
          if (crewMember.getAge() < 18) {
            crewMember =
                handleUnderageCrewMember(
                    crewMember,
                    crewMembersInCabin,
                    cabinCapacity,
                    spaceship,
                    explorationId,
                    cabinId);
          }
        }
        hashValues = hashValues.stream().map(n -> n + 1).toList();
      }
    }
    crewMemberRepository.saveAll(crewMembers);
  }

  private CrewMember handleUnderageCrewMember(
      CrewMember crewMember,
      List<Integer> crewMembersInCabin,
      Integer cabinCapacity,
      Spaceship spaceship,
      Integer explorationId,
      Integer cabinId) {
    // 1. Find one adult in the cabin to be relocated by crewMemberId
    CrewMember adultCrewMemberInCabin =
        crewMemberRepository.findFirstByAgeGreaterThanEqualAndIdIn(MIN_AGE,crewMembersInCabin).get(0);
    // 2. Insert underage crew member in the cabin
    insertCrewMember(
        crewMember, crewMembersInCabin, cabinCapacity - 1, spaceship, explorationId, cabinId);
    // 3. Return the adult crew member to be inserted in the cabin
    return adultCrewMemberInCabin;
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
