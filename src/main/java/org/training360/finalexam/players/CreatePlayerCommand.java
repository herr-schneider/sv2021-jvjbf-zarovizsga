package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.validators.Name;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePlayerCommand {

    @Name
    private String name;

    private LocalDate birthDay;

    private PositionType positionType;

    public CreatePlayerCommand(String name) {
        this.name = name;
    }

    public CreatePlayerCommand(String name, LocalDate birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }
}
