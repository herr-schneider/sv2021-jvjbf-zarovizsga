package org.training360.finalexam.players;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private PlayerRepo playerRepo;

    private ModelMapper modelMapper;

    public PlayerService(PlayerRepo playerRepo, ModelMapper modelMapper) {
        this.playerRepo = playerRepo;
        this.modelMapper = modelMapper;
    }

    public List<PlayerDTO> listAllPlayers() {
         List<PlayerDTO> allPlayers = playerRepo.findAll()
                .stream()
                .map(a -> modelMapper.map(a, PlayerDTO.class))
                .collect(Collectors.toList());
        return allPlayers;
    }

    public void delById(long id){
        playerRepo.deleteById(id);
    }

    public PlayerDTO createPlayer(CreatePlayerCommand createPlayerCommand) {
        Player player = new Player(createPlayerCommand.getName(),
                createPlayerCommand.getBirthDay(),
                createPlayerCommand.getPositionType());
        playerRepo.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }
}
