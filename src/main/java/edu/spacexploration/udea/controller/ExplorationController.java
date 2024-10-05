package edu.spacexploration.udea.controller;

import edu.spacexploration.udea.dto.CrewMemberExplDTO;
import edu.spacexploration.udea.entity.CrewMember;
import edu.spacexploration.udea.service.RandomHashingService;
import edu.spacexploration.udea.service.SampleCountingService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exploration")
@RequiredArgsConstructor
public class ExplorationController {

  private final SampleCountingService sampleCountingService;
  private final RandomHashingService randomHashingService;
  private final ModelMapper modelMapper;

  @PostMapping("/{explorationId}/spaceShip/{spaceShipId}/saveMembers")
  public void saveMembers(
      @PathVariable Integer explorationId,
      @PathVariable Integer spaceShipId,
      @RequestBody List<CrewMemberExplDTO> crewMembersDTO) {
    List<CrewMember> crewMembers =
        crewMembersDTO.stream()
            .map(crewMemberDTO -> modelMapper.map(crewMemberDTO, CrewMember.class))
            .toList();
    System.out.println("crewMembers = " + crewMembers);
    randomHashingService.saveMembers(explorationId, spaceShipId, crewMembers);
  }
}