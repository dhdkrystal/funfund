package com.service;

import com.entity.Position;
import com.entity.PositionKey;
import com.entity.Security;
import com.entity.SecurityKey;
import com.mapper.PositionMapper;
import com.mapper.SecurityMapper;
import com.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.util.Constant.*;


@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper mapper;

    /*
    Admin add security
     */
    public Error addPosition(Position position){
        Position position1;
        Error error ;
        PositionKey positionKey = new PositionKey();
        positionKey.setPortfolioName(position.getPortfolioName());
        positionKey.setSecurityName(position.getSecurityName());
        try{
            position1=mapper.selectByPrimaryKey(positionKey);
            if(position1 != null)
            {
                error= new Error(DUPLICATE_NAME,"Service:the position exists in the portfolio!");
            }else{
                mapper.insertSelective(position);
                error= new Error(ADD_SUCCESS,"Service:add position successfully!");
            }
        }catch (Exception e){
            e.printStackTrace();
            error= new Error(ADD_ERROR,"Service:failed to add position!");
        }
        return error;
    }


    /*
    Manager delete position
     */
    public Error deletePosition(PositionKey positionKey){
        Error error;
        try {
            mapper.deleteByPrimaryKey(positionKey);
            error = new Error(DELETE_SUCCESS,"Service:delete position successfully!");
        }catch (Exception e){
            e.printStackTrace();
            error = new Error(DELETE_ERROR,"Service:Failed to delete position!");
        }
        return error;
    }

    /*
    *Manager update position
     */
//    public Error updatePosition(Position position){
//        Error error;
//        try {
//            mapper.updateByPrimaryKeySelective(position);
//            error = new Error(UPDATE_SUCCESS,"Service:update position successfully!");
//        }catch (Exception e){
//            e.printStackTrace();
//            error = new Error(UPDATE_ERROR,"Service:Failed to update position!");
//        }
//        return error;
//    }


    /*
    *Manager operation: get position list of a portfolio
     */
    public List<Position> getPositionList(String portfolioName){
        List<Position> positionList = mapper.selectPositionByPortfolio(portfolioName);
        if(positionList == null){
            return null;
        }
       return positionList;
    }

}
