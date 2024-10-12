package edu.spacexploration.udea.service.impl;

import edu.spacexploration.udea.entity.CrewMember;
import edu.spacexploration.udea.repository.CrewMemberRepository;
import edu.spacexploration.udea.service.SampleCountingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SampleCountingServiceImpl implements SampleCountingService {
  private final CrewMemberRepository crewMemberRepository;

  @Override
  public List<CrewMember> findAll() {
    List<CrewMember> crewMembers=crewMemberRepository.findAll();
    System.out.println("Crew Members: "+crewMembers);
    return crewMembers;
  }
}
