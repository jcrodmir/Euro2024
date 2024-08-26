package com.Euro2024.controller;

import com.Euro2024.dto.GenericResponse;
import com.Euro2024.dto.TeamDto;
import com.Euro2024.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class TeamController {

    private TeamService teamService;
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("team")
    public ResponseEntity<GenericResponse<TeamDto>> getTeams(@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize) {

        return new ResponseEntity<GenericResponse<TeamDto>>(teamService.getAllTeam(pageNo,pageSize), HttpStatus.OK);
    }

    @GetMapping("team/{id}")
    public ResponseEntity<TeamDto> teamDetail(@PathVariable int id){
        return ResponseEntity.ok(teamService.getTeamById(id));

    }

    //RequestBody
    @PostMapping("team/create")
    @ResponseStatus(HttpStatus.CREATED)
    public  ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto team){

        return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.CREATED);
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
