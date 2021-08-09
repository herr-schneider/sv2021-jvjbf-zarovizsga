package org.training360.finalexam.teams;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private TeamRepo teamRepo;

    private PlayerRepo playerRepo;

    private ModelMapper modelMapper;

    public TeamService(TeamRepo teamRepo, PlayerRepo playerRepo, ModelMapper modelMapper) {
        this.teamRepo = teamRepo;
        this.playerRepo = playerRepo;
        this.modelMapper = modelMapper;
    }

    public List<TeamDTO> listAllTeams() {
        List<TeamDTO> allPlayers = teamRepo.findAll()
                .stream()
                .map(a -> modelMapper.map(a, TeamDTO.class))
                .collect(Collectors.toList());
        return allPlayers;
    }

    public void delById(long id) {
        teamRepo.deleteById(id);
    }

    public TeamDTO createTeam(CreateTeamCommand createTeamCommand) {
        Team team = new Team(createTeamCommand.getName());
        teamRepo.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO updateWithId(long id, CreatePlayerCommand createPlayerCommand) {
        Player player = new Player(createPlayerCommand.getName(),
                createPlayerCommand.getBirthDay(),
                createPlayerCommand.getPositionType());
        Team team = teamRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no " + id + " ID team"));
        team.addPlayer(player);
        teamRepo.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    public TeamDTO addPlayerToTeam(UpdateWithExistingPlayerCommand command) {
        Player player = playerRepo.findById(command.getPlayerId())
                .orElseThrow(() -> new IllegalArgumentException("There is no " + command.getPlayerId() + " ID player"));

        Team team = teamRepo.findById(command.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("There is no " + command.getTeamId() + " ID team"));

        long numOfPos = team.getPlayers()
                .stream()
                .filter(p -> p.getPosition()==player.getPosition())
                .count();
        if (player.getTeam() != null && numOfPos < 2) {team.addPlayer(player);}

        return modelMapper.map(team, TeamDTO.class);
    }
}
