package com.Euro2024.controller;

import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.GenericResponse;
import com.Euro2024.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")//Structure to call requests.
public class PlayerController {
    private PlayerService playerService;
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("player")
    public ResponseEntity<GenericResponse<PlayerDto>> getPlayers(@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize) {

        return new ResponseEntity<GenericResponse<PlayerDto>>(playerService.getAllPlayer(pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("player/{id}")
    public ResponseEntity<PlayerDto> playerDetail(@PathVariable int id){
        return ResponseEntity.ok(playerService.getPlayerById(id));

    }
    @GetMapping("player/team/{teamId}")
    public ResponseEntity<List<PlayerDto>> teamPlayersDetail(@PathVariable int teamId){
        return ResponseEntity.ok(playerService.getAllPlayersFromTeam(teamId));

    }

    //RequestBody
    @PostMapping("player/{id}/create")
    @ResponseStatus(HttpStatus.CREATED)
    public  ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto player,@PathVariable("id") int teamId){

        return new ResponseEntity<>(playerService.createPlayer(player,teamId), HttpStatus.CREATED);
    }


    @PutMapping("player/update/{id}")
    public  ResponseEntity<PlayerDto> updatePlayer(@RequestBody PlayerDto player,@PathVariable("id") int playerId){

        PlayerDto response= playerService.updatePlayer(player,playerId);

        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("player/{id}/delete")
    public  ResponseEntity<String> deletePlayer(@PathVariable("id") int playerId){

        playerService.deletePlayer(playerId);


        return  ResponseEntity.ok("PLayer was deleting");
    }

}
