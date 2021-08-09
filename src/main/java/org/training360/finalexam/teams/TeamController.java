package org.training360.finalexam.teams;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.training360.finalexam.players.CreatePlayerCommand;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "There is not any of team!")
    public List<TeamDTO> listAllTeams() {
        return teamService.listAllTeams();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTrainingClass(@Valid @RequestBody CreateTeamCommand createTeamCommand) {
        return teamService.createTeam(createTeamCommand);
    }

    @PostMapping("/{id}/players")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "Team not found")
    public ResponseEntity updateWithId(@PathVariable("id") long id,
                                       @Valid @RequestBody CreatePlayerCommand createPlayerCommand) {
        try {
            return ResponseEntity.ok(teamService.updateWithId(id, createPlayerCommand));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/players")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "404", description = "Package not found")
    public ResponseEntity addPlayerToTeam(@RequestBody UpdateWithExistingPlayerCommand command) {
        try {
            return ResponseEntity.ok(teamService.addPlayerToTeam(command));
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Problem> handleValidException(MethodArgumentNotValidException mnve) {
        List<Violation> violations = mnve.getBindingResult().getFieldErrors().stream()
                .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        Problem problem = Problem.builder()
                .withType(URI.create("/api/registry/not-valid"))
                .withTitle("Validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(mnve.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem = Problem.builder()
                .withType(URI.create("/api/registry/param"))
                .withTitle("Not found!")
                .withStatus(Status.NOT_FOUND)
                .withDetail(iae.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
