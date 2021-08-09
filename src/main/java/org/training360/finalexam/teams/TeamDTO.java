package org.training360.finalexam.teams;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerDTO;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {

    private Long id;

    private String name;

    @OneToMany(cascade = {PERSIST, REMOVE}, mappedBy = "team")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonBackReference
    private List<PlayerDTO> players;

}
