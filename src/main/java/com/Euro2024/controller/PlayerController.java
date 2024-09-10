package com.Euro2024.controller;

import com.Euro2024.dto.PlayerDto;
import com.Euro2024.dto.GenericResponse;
import com.Euro2024.models.PlayerEntity;
import com.Euro2024.service.PlayerService;
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
@RequestMapping("/v1/")//Structure to call requests.
public class PlayerController {
    private PlayerService playerService;
    private final Bucket bucket;
    private final int tokens=1;
    private final int capacity=200;



    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
        Bandwidth limit=Bandwidth.classic(capacity, Refill.greedy(tokens, Duration.ofMinutes(1)));

        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @GetMapping("player")
    public ResponseEntity<GenericResponse<PlayerDto>> getPlayers(@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
                                                      @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize) {
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
         return new ResponseEntity<GenericResponse<PlayerDto>>(playerService.getAllPlayer(pageNo,pageSize),HttpStatus.OK);
    }

    @GetMapping("player/id/{id}")
    public ResponseEntity<PlayerDto> playerDetail(@PathVariable int id){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        return ResponseEntity.ok(playerService.getPlayerById(id));

    }

    @GetMapping("player/name")
    public ResponseEntity<PlayerDto> playerName(@RequestParam(value = "name", defaultValue = "unknown",required = false) String name){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }

        return ResponseEntity.ok(playerService.getPlayerByName(name));

    }
    @GetMapping("player/goal")
    public ResponseEntity<List<PlayerDto>> playerGoal(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberByGoals(number));

    }
    @GetMapping("player/goals")
    public ResponseEntity<List<PlayerDto>> playerGoals(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByGoals());

    }
    @GetMapping("player/yellowcard")
    public ResponseEntity<List<PlayerDto>> playerYellow(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberYellowCards(number));

    }
    @GetMapping("player/yellowcards")
    public ResponseEntity<List<PlayerDto>> playerYellowCard(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByYellowCard());

    }

    @GetMapping("player/redcard")
    public ResponseEntity<List<PlayerDto>> playerRed(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberRedCards(number));

    }
    @GetMapping("player/redcards")
    public ResponseEntity<List<PlayerDto>> playerRedCard(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByRedCard());

    }
    @GetMapping("player/save")
    public ResponseEntity<List<PlayerDto>> playerGoalKeeper(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberGoalKeeperSaves(number));

    }
    @GetMapping("player/saves")
    public ResponseEntity<List<PlayerDto>> playerGoalKeepers(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByGoalKeeperSaves());

    }
    @GetMapping("player/assist")
    public ResponseEntity<List<PlayerDto>> playerAssist(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberAssists(number));

    }
    @GetMapping("player/assists")
    public ResponseEntity<List<PlayerDto>> playerAssists(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByAssists());

    }
    @GetMapping("player/recover")
    public ResponseEntity<List<PlayerDto>> playerRecovered(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberRecovered(number));

    }
    @GetMapping("player/recovered")
    public ResponseEntity<List<PlayerDto>> playerrecovered(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByRecovered());

    }
    @GetMapping("player/dorsal")
    public ResponseEntity<List<PlayerDto>> playerDorsal(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberByDorsal(number));

    }
    @GetMapping("player/dorsals")
    public ResponseEntity<List<PlayerDto>> playerDorsals(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByDorsal());

    }

    @GetMapping("player/minute")
    public ResponseEntity<List<PlayerDto>> playerMinutes(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberMinutes(number));

    }
    @GetMapping("player/minutes")
    public ResponseEntity<List<PlayerDto>> playerMinutes(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByMinutes());

    }
    @GetMapping("player/match")
    public ResponseEntity<List<PlayerDto>> playerMatch(@RequestParam(value = "number", defaultValue = "0" ,required = false) int number){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerNumberMatches(number));

    }
    @GetMapping("player/matches")
    public ResponseEntity<List<PlayerDto>> playerMatches(){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByMatches());

    }
    @GetMapping("player/position")
    public ResponseEntity<?> playerPosition(@RequestParam(value = "position", defaultValue = "0" ,required = false) PlayerEntity.Position position){
        List<PlayerDto> player = playerService.getPlayerByPosition(position);
        if(player.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No players found for the given position");
        }
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
        return ResponseEntity.ok(playerService.getPlayerByPosition(position));

    }


    @GetMapping("player/team/{teamId}")
    public ResponseEntity<List<PlayerDto>> teamPlayersDetail(@PathVariable int teamId){
        if (!bucket.tryConsume(tokens)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
        }
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
