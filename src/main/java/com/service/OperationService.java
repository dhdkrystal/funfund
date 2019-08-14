package com.service;

import com.entity.Operation;
import com.util.Error;

import java.util.List;

public interface OperationService {
    /**
     *do add operation
     * @param operation  the entity of Operation
     */
    Error addOperation(Operation operation);


}
