package com.Euro2024.controller;

import com.Euro2024.dto.PlayerDto;
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
    public ResponseEntity<List<PlayerDto>> getPlayers() {

        return new ResponseEntity<List<PlayerDto>>(playerService.getAllPlayer(1,2),HttpStatus.OK);
    }

    @GetMapping("player/{id}")
    public ResponseEntity<PlayerDto> playerDetail(@PathVariable int id){
        return ResponseEntity.ok(playerService.getPlayerById(id));

    }

    //RequestBody
    @PostMapping("player/create")
    @ResponseStatus(HttpStatus.CREATED)
    public  ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto player){

        return new ResponseEntity<>(playerService.createPlayer(player), HttpStatus.CREATED);
    }


    @PutMapping("player/{id}/update")
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
