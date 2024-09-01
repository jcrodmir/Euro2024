package com.Euro2024.controller;

import com.Euro2024.dto.GenericResponse;
import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.TeamDto;
import com.Euro2024.models.PlayerEntity;
import com.Euro2024.service.TeamService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class TeamController {

    private TeamService teamService;
    private final Bucket bucket;
    private final int tokens=10;
    private final int capacity=10;

    @Autowired
    public TeamController(TeamService teamService) {

        this.teamService = teamService;
        Bandwidth limit=Bandwidth.classic(capacity, Refill.greedy(tokens, Duration.ofMinutes(1)));

        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @GetMapping("team")
    public ResponseEntity<GenericResponse<TeamDto>> getTeams(@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize) {
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return new ResponseEntity<GenericResponse<TeamDto>>(teamService.getAllTeam(pageNo,pageSize), HttpStatus.OK);
    }

    @GetMapping("team/{id}")
    public ResponseEntity<TeamDto> teamDetail(@PathVariable int id){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getTeamById(id));

    }

    //RequestBody
    @PostMapping("team/create")
    @ResponseStatus(HttpStatus.CREATED)
    public  ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto team){
        return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.CREATED);
    }
    /////////////////////////////////////////////////////////////////
    @GetMapping("team/players")
    public ResponseEntity<List<PlayerDto>> teamOnlyPlayers(@RequestParam(value = "id", defaultValue = "0" ,required = false) int id){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getPlayersByTeamId(id));

    }
    @GetMapping("team/country")
    public ResponseEntity<TeamDto> getTeamByCountry(@RequestParam(value = "country", defaultValue = "" ,required = false) String country){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getByCountry(country));

    }
    @GetMapping("team/{country}/coach")
    public ResponseEntity<String> getCoachbyTeam(@PathVariable String country) {
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getCoachByCountry(country));
    }
    @GetMapping("team/{country}/federation")
    public ResponseEntity<String> getFederationbyTeam(@PathVariable String country) {
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getFederationByCountry(country));
    }
    @GetMapping("team/{country}/championships")
    public ResponseEntity<Integer> getChampionshipsbyTeam(@PathVariable String country) {
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getChampionshipsByCountry(country));
    }

    @GetMapping("team/coach")
    public ResponseEntity<TeamDto> getTeamByCoach(@RequestParam(value = "coach", defaultValue = "" ,required = false) String coach) {
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getByCoach(coach));
    }
    @GetMapping("team/federation")
    public ResponseEntity<TeamDto> getTeamByFederation(@RequestParam(value = "federation", defaultValue = "" ,required = false) String federation){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getByFederation(federation));

    }
    @GetMapping("team/championships")
    public ResponseEntity<TeamDto> getTeamByChampionships(@RequestParam(value = "championships", defaultValue = "0" ,required = false) int championships){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getByChampionships(championships));

    }
    @GetMapping("team/foundation")
    public ResponseEntity<TeamDto> getTeamByFoundation(@RequestParam(value = "foundation", defaultValue = "0" ,required = false) int foundation){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(teamService.getByFoundation(foundation));

    }


    @PutMapping("team/{id}/update")
    public  ResponseEntity<TeamDto> updateTeam(@RequestBody TeamDto team,@PathVariable("id") int teamId){

        TeamDto response= teamService.updateTeam(team,teamId);

        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("team/{id}/delete")
    public  ResponseEntity<String> deleteTeam(@PathVariable("id") int teamId){

        teamService.deleteTeam(teamId);


        return  ResponseEntity.ok("Team was deleting");
    }
}
