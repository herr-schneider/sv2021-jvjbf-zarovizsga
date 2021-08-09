package org.training360.finalexam.teams;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training360.finalexam.players.Player;

public interface TeamRepo extends JpaRepository<Team, Long> {
}
