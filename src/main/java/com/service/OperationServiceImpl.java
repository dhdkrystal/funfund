package com.service;

import com.entity.*;
import com.mapper.OperationMapper;
import com.mapper.SecurityMapper;
import com.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.util.Constant.ADD_ERROR;
import static com.util.Constant.ADD_SUCCESS;
import static com.util.Constant.DUPLICATE_NAME;


@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationMapper mapper;
    @Autowired
    PositionService positionService;

    /*
    *Manager: add operation
     */
    public Error addOperation(Operation operation){
        Error error;
        Operation operation1 = new Operation();
        OperationKey operationKey = new OperationKey();
        operationKey.setPortfolioName(operation.getPortfolioName());
        operationKey.setSecurityName(operation.getSecurityName());
        operationKey.setDate(operation.getDate());
        try{
            operation1 = mapper.selectByPrimaryKey(operationKey);
            if(operation1 != null){
                error= new Error(DUPLICATE_NAME,"Service:the operation already exists!");
            }else{
                mapper.insertSelective(operation);
                error= new Error(ADD_SUCCESS,"Service:add operation successfully!");

//                according the operation to change the position
                positionService.positionChangeByOperation(operation);

            }
        }catch(Exception e){
            e.printStackTrace();
            error = new Error(ADD_ERROR, "Service:failed to add operation!");
        }
        System.out.println(error.getMessage());
        return error;
    }





}
