package edu.spacexploration.udea.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "crew_member")
public class CrewMember {

  @Id private String id;

  @Column(name = "family_id")
  private Integer familyId;

  private String name;

  private Integer age;

  private String surname;

  private String gender;

  @Column(name = "civil_status")
  private String civilStatus;

  private Integer children;

  private Float salary;

  @Column(name = "trips_achieved")
  private Integer tripsAchieved;
}
