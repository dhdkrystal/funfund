package com.service;

import com.entity.Operation;
import com.entity.Position;
import com.entity.PositionKey;
import com.util.Error;

import java.util.List;

public interface PositionService {
    /**
     *Insert the position
     * @param position  the entity of Position
     */
    Error addPosition(Position position);

    /**
     *Delete the position
     * @param positionKey  the primary key of Position
     */
    Error deletePosition(PositionKey positionKey);

    /*Update the position
     * @param position  the entity of Position
     */
    Error updatePosition(Position position);

    /*
    *Select position list of a portfolio
     */
    List<Position> getPositionList(String portfolioName);


    void positionChangeByOperation(Operation operation);

    Error updatePositionClose();
}
