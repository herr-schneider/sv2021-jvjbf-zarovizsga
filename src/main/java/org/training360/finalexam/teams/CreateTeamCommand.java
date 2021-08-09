package org.training360.finalexam.teams;

import lombok.*;
import org.training360.finalexam.validators.Name;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTeamCommand {

    @Name
    private String name;
}

