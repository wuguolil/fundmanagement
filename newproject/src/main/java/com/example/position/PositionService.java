package com.example.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    public Position getPosition(int id) {

        Optional<Position> positionOptional = positionRepository.findById(id);
        if(positionOptional.isEmpty()){
            throw new PositionNotFoundException(id);
        }
        else
        {
            return positionOptional.get();
        }
    }

    public void addPosition(Position position) {
        positionRepository.save(position);
    }

    public void deletePosition(int id) {
        if(positionRepository.existsById(id)){
            positionRepository.deleteById(id);
        }
        else{
            throw new PositionNotFoundException(id);
        }
    }

    public void updatePosition(int id, Position position) {
        Optional<Position> positionOptional = positionRepository.findById(id);
        if(positionOptional.isEmpty()){
            throw new PositionNotFoundException(id);
        }
        Position updatePosition = positionOptional.get();

        if(position.getPositionId() != updatePosition.getPositionId()){
            throw new IllegalStateException("Position Id in path and in request body is different.");
        }

        if(!Objects.equals(position.getSecurity(), updatePosition.getSecurity()))
            updatePosition.setSecurity(position.getSecurity());

        if(!Objects.equals(position.getQuantity(), updatePosition.getQuantity()))
            updatePosition.setQuantity(position.getQuantity());

        if(!Objects.equals(position.getDatePurchased(), updatePosition.getDatePurchased()))
            updatePosition.setDatePurchased(position.getDatePurchased());

        if(!Objects.equals((position.getFund()),updatePosition.getFund()))
            updatePosition.setFund(position.getFund());

        if(!Objects.equals((position.getSecurity()),updatePosition.getSecurity()))
            updatePosition.setSecurity(position.getSecurity());


        positionRepository.save(updatePosition);

    }
}
