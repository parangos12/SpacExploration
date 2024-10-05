package edu.spacexploration.udea.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "spaceship")
public class Spaceship {

  @Id
  private Integer id;

  private Integer capacity;

  @Column(name="cabin_capacity")
  private Integer cabinCapacity;

}
