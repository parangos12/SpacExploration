package edu.spacexploration.udea.util;

import edu.spacexploration.udea.entity.CrewMember;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CrewMemberKey implements Serializable {

  private Integer id;

  @Column(name = "cabin_id")
  private Integer cabinId;

  @ManyToOne
  @JoinColumn(name="crew_member_id")
  private CrewMember crewMember;

}
