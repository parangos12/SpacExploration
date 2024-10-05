package edu.spacexploration.udea.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CrewMemberExplDTO {

  @JsonProperty("_id")
  private String id;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("Surname")
  private String surname;

  @JsonProperty("Age")
  private Integer age;

  @JsonProperty("Gender")
  private String gender;

  @JsonProperty("FamilyID")
  private Integer familyId;

  @JsonProperty("CivilStatus")
  private String civilStatus;

  @JsonProperty("Children")
  private Integer children;

  @JsonProperty("TripsAchieved")
  private Integer tripsAchieved;

  @JsonProperty("Salary")
  private Float salary;
}
