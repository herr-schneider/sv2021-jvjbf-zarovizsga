package org.training360.finalexam.teams;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTeamCommand {

    @NotNull
    private String name;
}

