package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.players.Player;

@NoArgsConstructor
@Data
public class UpdateWithExistingPlayerCommand {

    private long playerId;

    public UpdateWithExistingPlayerCommand(Long playerId){
        this.playerId = playerId;
    }

}
