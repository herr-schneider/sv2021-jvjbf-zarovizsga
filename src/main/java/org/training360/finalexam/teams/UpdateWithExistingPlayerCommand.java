package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.players.Player;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateWithExistingPlayerCommand {

    private long playerId;

    private long teamId;

    public UpdateWithExistingPlayerCommand(Long id) {
    }
}
