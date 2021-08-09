package org.training360.finalexam.players;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.training360.finalexam.teams.Team;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerDTO {

//    @JsonIgnore
    private Long id;

    private String name;

    private LocalDate birthDate;

    private PositionType position;

    @EqualsAndHashCode.Exclude
   @ToString.Exclude
//    @JsonIgnore
    @JsonBackReference
    private Team team;

}

