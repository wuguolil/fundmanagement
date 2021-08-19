package com.example.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> getPositions(){
        return positionService.getPositions();
    }

    @GetMapping(path = "{positionId}")
    public Position getPosition(@PathVariable("positionId") int id){
        return positionService.getPosition(id);
    }

    @PostMapping
    public void addPosition(@RequestBody Position position){
        positionService.addPosition(position);
    }

    @DeleteMapping(path = "{positionId}")
    public void deletePosition(@PathVariable("positionId") int id){
        positionService.deletePosition(id);
    }

    @PutMapping(path = "{positionId}")
    public void updatePosition(@PathVariable("positionId") int id, @RequestBody Position position){
        positionService.updatePosition(id,position);
    }
}
