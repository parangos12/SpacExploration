package edu.spacexploration.udea.entity;

import edu.spacexploration.udea.util.CrewMemberKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exploration")
public class Exploration {

  @EmbeddedId private CrewMemberKey id;

  @ManyToOne
  @JoinColumn(name="spaceship_id")
  private Spaceship spaceship;

  @ManyToOne
  @JoinColumn(name="crew_member_id")
  private CrewMember crewMember;

}
